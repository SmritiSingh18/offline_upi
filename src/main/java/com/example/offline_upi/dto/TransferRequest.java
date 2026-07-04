package com.example.offline_upi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private String senderWalletNumber;
    private String receiverWalletNumber;
    private BigDecimal amount;
    
}
