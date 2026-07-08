package com.example.offline_upi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementResponse {
   private String packetHash;
   private String status;
   private String message;

}
