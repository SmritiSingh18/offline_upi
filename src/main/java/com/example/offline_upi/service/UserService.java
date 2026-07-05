package com.example.offline_upi.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.RegisterUserRequest;
import com.example.offline_upi.dto.UserResponse;
import com.example.offline_upi.entity.Wallet;
import com.example.offline_upi.entity.User;
import com.example.offline_upi.enums.WalletStatus;
import com.example.offline_upi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
     
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserResponse registerUser(RegisterUserRequest request){
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
