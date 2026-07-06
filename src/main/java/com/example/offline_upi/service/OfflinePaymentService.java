package com.example.offline_upi.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.OfflinePaymentRequest;
import com.example.offline_upi.dto.OfflinePaymentResponse;
import com.example.offline_upi.entity.OfflinePaymentPacket;
import com.example.offline_upi.repository.OfflinePaymentPacketRepository;
import com.example.offline_upi.util.EncryptionUtil;

@Service
public class OfflinePaymentService {
    private final OfflinePaymentPacketRepository packetRepository;
    private final EncryptionUtil encryptionUtil;

    OfflinePaymentService(OfflinePaymentPacketRepository packetRepository,EncryptionUtil encryptionUtil){
        this.packetRepository=packetRepository;
        this.encryptionUtil=encryptionUtil;
    }

    public OfflinePaymentResponse createOfflinePayment(OfflinePaymentRequest request){
        String packetId=UUID.randomUUID().toString();

        String payload=packetId+"|"+request.getSenderWalletNumber()+"|"+request.getReceiverWalletNumber()+"|"+request.getAmount();
        String encrypted=encryptionUtil.encrypt(payload);

        OfflinePaymentPacket packet=new OfflinePaymentPacket();
        packet.setPacketId(packetId);
        packet.setEncryptedPayLoad(encrypted);
        packet.setCreatedAt(LocalDateTime.now());
        packet.setSynced(false);
        packetRepository.save(packet);

        return new OfflinePaymentResponse(packetId, encrypted);
    }

    
}
