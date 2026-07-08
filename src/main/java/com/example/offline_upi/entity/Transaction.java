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
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderWalletNumber;

    private String receiverWalletNumber;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    
    private LocalDateTime createdAt;

    private LocalDateTime syncedAt;

    @PrePersist
    public void beforeSave(){
        this.createdAt=LocalDateTime.now();
    }
    
}
