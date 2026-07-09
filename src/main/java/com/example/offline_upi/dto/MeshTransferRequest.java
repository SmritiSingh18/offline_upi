package com.example.offline_upi.dto;

import lombok.Data;

@Data
public class MeshTransferRequest {
    private String packetHash;
    private String fromDevice;
    private String toDevice;
}
