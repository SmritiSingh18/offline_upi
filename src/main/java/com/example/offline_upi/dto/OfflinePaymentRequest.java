package com.example.offline_upi.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OfflinePaymentRequest {
    private String senderWalletNumber;
    private String receiverWalletNumber;
    private BigDecimal amount;
}
