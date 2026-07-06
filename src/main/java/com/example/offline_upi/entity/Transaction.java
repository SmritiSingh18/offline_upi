package com.example.offline_upi.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.offline_upi.enums.TransactionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderWalletNumber;

    private String receiverWalletNumber;
     
    @Column(unique = true)
    private String packetId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    
    private LocalDateTime transactionTime;

    private LocalDateTime syncedAt;
    
}
