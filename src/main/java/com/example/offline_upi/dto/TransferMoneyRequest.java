package com.example.offline_upi.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferMoneyRequest {
    private String senderWalletNumber;
    private String receiverWalletNumber;
    private BigDecimal amount;
}
