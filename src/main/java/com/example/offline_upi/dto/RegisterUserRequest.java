package com.example.offline_upi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    private String fullname;
    private String phoneNumber;
    private String upiId;
    private String pin;
}
