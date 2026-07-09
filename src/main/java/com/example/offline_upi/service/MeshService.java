package com.example.offline_upi.service;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.MeshResponse;
import com.example.offline_upi.dto.MeshTransferRequest;
import com.example.offline_upi.entity.OfflinePaymentPacket;
import com.example.offline_upi.repository.OfflinePaymentPacketRepository;

@Service
public class MeshService {
    private final OfflinePaymentPacketRepository packetRepository;
    MeshService(OfflinePaymentPacketRepository packetRepository){
        this.packetRepository=packetRepository;
    }

    public MeshResponse relayPacket(MeshTransferRequest request){
        OfflinePaymentPacket packet=packetRepository.findByPacketHash(request.getPacketHash())
                                    .orElseThrow(
                                        ()-> new RuntimeException("Packet not found")
                                    );

        return MeshResponse.builder()
                            .packetHash(packet.getPacketHash())
                            .message("Packet relayed from "+request.getFromDevice()+" to "+request.getToDevice()+" successfully")
                            .build();
    }
}
