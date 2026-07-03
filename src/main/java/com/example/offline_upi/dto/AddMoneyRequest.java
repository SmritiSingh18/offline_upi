package com.example.offline_upi.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AddMoneyRequest {
   private String phoneNumber;
   private BigDecimal amount; 
}
