package com.example.offline_upi.service;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.RegisterUserRequest;
import com.example.offline_upi.dto.UserResponse;
import com.example.offline_upi.entity.user;
import com.example.offline_upi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
     
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserResponse registerUser(RegisterUserRequest request){
       user User =new user();
       User.setFullname(request.getName());
       User.setPhonenumber(request.getPhoneNumber());
       User.setUpiId(request.getUpiId());
       User.setWalletBalance(0.0);

       user savedUser=userRepository.save(User);
       UserResponse response=new UserResponse();

       response.setId(savedUser.getId());
       response.setName(savedUser.getFullname());
       response.setPhoneNumber(savedUser.getPhonenumber());
       response.setUpiId(savedUser.getUpiId());
       response.setWalletBalance(savedUser.getWalletBalance());

       return response;
    }


}
