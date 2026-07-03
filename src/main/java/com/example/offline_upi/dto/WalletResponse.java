package com.example.offline_upi.dto;

import java.math.BigDecimal;

import com.example.offline_upi.enums.WalletStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponse {
    private String walletNumber;
    private BigDecimal balance;
    private WalletStatus Status;
    
    
}
