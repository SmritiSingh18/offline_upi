package com.example.offline_upi.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.MeshRequest;
import com.example.offline_upi.dto.MeshResponse;
import com.example.offline_upi.entity.MeshNode;
import com.example.offline_upi.repository.MeshNodeRepository;

@Service
public class MeshService {
    private final MeshNodeRepository meshNodeRepository;
    MeshService(MeshNodeRepository meshNodeRepository){
        this.meshNodeRepository=meshNodeRepository;
    }
    public MeshResponse relayPacket(MeshRequest request){
        MeshNode node=new MeshNode();
        node.setNodeId(request.getNodeId());
        node.setPacketId(request.getPacketId());
        node.setEncryptedPayLoad(request.getEncryptedPayLoad());
        node.setHasInternet(request.isHasInternet());
        node.setReceivedAt(LocalDateTime.now());

        return new MeshResponse(
            "Packet relayed successfully",
            node.getNodeId(),
            node.getPacketId()
        );
    }
    
}
