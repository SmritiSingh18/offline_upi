package com.example.offline_upi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "offline_packets")
@Builder
public class OfflinePaymentPacket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String packetHash;
     
    @Column(nullable =  false)
    private String receiverWalletNumber;

    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String encryptedKey;

    @Column(nullable = false)
    private String iv;

    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String cipherText;

    private boolean synced=false;

    private LocalDateTime createdAt;

    @PrePersist
    public void beforeSave(){
        this.createdAt=LocalDateTime.now();
    }
}
