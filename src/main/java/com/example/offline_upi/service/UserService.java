package com.example.offline_upi.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.RegisterUserRequest;
import com.example.offline_upi.dto.UserResponse;
import com.example.offline_upi.entity.Wallet;
import com.example.offline_upi.entity.user;
import com.example.offline_upi.enums.WalletStatus;
import com.example.offline_upi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
     
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserResponse registerUser(RegisterUserRequest request){
       user User =new user();
       User.setFullname(request.getFullname());
       User.setPhoneNumber(request.getPhoneNumber());
       User.setUpiId(request.getUpiId());
       User.setPin(request.getPin());
       
       Wallet wallet=new Wallet();

       wallet.setWalletNumber(generateWalletNumber());
       wallet.setBalance(BigDecimal.ZERO);
       wallet.setStatus(WalletStatus.ACTIVE);

       User.setWallet(wallet);
       wallet.setUser(User);


       user savedUser=userRepository.save(User);
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
