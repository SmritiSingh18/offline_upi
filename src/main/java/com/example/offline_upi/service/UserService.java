package com.example.offline_upi.service;

import java.math.BigDecimal;
import java.security.KeyPair;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.RegisterUserRequest;
import com.example.offline_upi.dto.UserResponse;
import com.example.offline_upi.entity.Wallet;
import com.example.offline_upi.entity.User;
import com.example.offline_upi.enums.WalletStatus;
import com.example.offline_upi.repository.UserRepository;
import com.example.offline_upi.util.RSAUtil;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RSAUtil rsaUtil;
     
    public UserService(UserRepository userRepository,RSAUtil rsaUtil){
        this.userRepository=userRepository;
        this.rsaUtil=rsaUtil;
    }

    public UserResponse registerUser(RegisterUserRequest request) throws Exception{
       User user =new User();
       user.setFullname(request.getFullname());
       user.setPhoneNumber(request.getPhoneNumber());
       user.setUpiId(request.getUpiId());
       user.setPin(request.getPin());
       
       Wallet wallet=new Wallet();

       wallet.setWalletNumber(generateWalletNumber());
       wallet.setBalance(BigDecimal.ZERO);
       wallet.setStatus(WalletStatus.ACTIVE);

       user.setWallet(wallet);
       wallet.setUser(user);

       KeyPair keyPair=rsaUtil.generateKeyPair();
       user.setPublicKey(rsaUtil.encodePublicKey(keyPair.getPublic()));
       user.setPrivateKey(rsaUtil.encodePrivateKey(keyPair.getPrivate()));


       User savedUser=userRepository.save(user);
       UserResponse response=new UserResponse();

       response.setId(savedUser.getId());
       response.setName(savedUser.getFullname());
       response.setPhoneNumber(savedUser.getPhoneNumber());
       response.setUpiId(savedUser.getUpiId());
       
       response.setWalletNumber(savedUser.getWallet().getWalletNumber());
       response.setWalletBalance(savedUser.getWallet().getBalance());
       return response;
    }
    private String generateWalletNumber(){
        return "WAL"+System.currentTimeMillis();
    }


}
