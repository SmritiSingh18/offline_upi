package com.example.offline_upi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OfflinePaymentResponse {
    private String packetId;
    private String encryptedPayLoad;
}
