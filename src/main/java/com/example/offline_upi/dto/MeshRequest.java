package com.example.offline_upi.dto;

import lombok.Data;

@Data

public class MeshRequest {
    private String nodeId;
    private String packetId;
    private String encryptedPayLoad;
    private boolean hasInternet;
    
}
