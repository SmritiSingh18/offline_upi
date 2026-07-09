package com.example.offline_upi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeshResponse {
    private String packetHash;
    private String message;
}
