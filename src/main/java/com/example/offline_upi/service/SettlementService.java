package com.example.offline_upi.service;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.SettlementResponse;
import com.example.offline_upi.entity.OfflinePaymentPacket;
import com.example.offline_upi.repository.OfflinePaymentPacketRepository;
import com.example.offline_upi.repository.WalletRepository;

@Service
public class SettlementService {
    private final OfflinePaymentPacketRepository packetRepository;
    SettlementService(OfflinePaymentPacketRepository packetRepository){
        this.packetRepository=packetRepository;
    }
    public SettlementResponse settlePayment(String packetHash){
        OfflinePaymentPacket packet=packetRepository.findByPacketHash(packetHash)
                                    .orElseThrow(
                                        () -> new RuntimeException("Packet not found")
                                    );
        if (packet.isSynced()) {
            return SettlementResponse.builder()
                                      .packetHash(packetHash)
                                      .status("FAILED")
                                      .message("Packet already settled")
                                      .build();
            
        }
        packet.setSynced(true);
        packetRepository.save(packet);
        return SettlementResponse.builder()
                                .packetHash(packetHash)
                                .status("SUCCESS")
                                .message("Offline payment settled successfully")
                                .build();
    }
    
}
