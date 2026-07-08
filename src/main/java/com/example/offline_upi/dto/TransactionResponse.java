package com.example.offline_upi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.offline_upi.enums.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private Long transactionId;
    private String senderWalletNumber;
    private String receiverWalletNumber;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime createdAt;

}
