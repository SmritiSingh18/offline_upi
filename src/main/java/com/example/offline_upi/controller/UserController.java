package com.example.offline_upi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.offline_upi.dto.RegisterUserRequest;
import com.example.offline_upi.dto.UserResponse;
import com.example.offline_upi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody RegisterUserRequest request) {
       
        return userService.registerUser(request);
    }
    
}
