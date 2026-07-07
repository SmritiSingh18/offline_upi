package com.example.offline_upi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeshResponse {
    private String message;
    private String nodeId;
    private String packetId;
}
