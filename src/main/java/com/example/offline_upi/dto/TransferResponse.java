package com.example.offline_upi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.transaction.TransactionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {
    private TransactionStatus status;
    private String referenceId;
    private BigDecimal amount;
    private LocalDateTime transactionTime;
    
}
