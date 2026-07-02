package com.example.offline_upi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String upiId;
    private BigDecimal walletBalance;
    private String walletNumber;

}
