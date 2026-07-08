package com.example.offline_upi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.offline_upi.dto.SettlementResponse;
import com.example.offline_upi.service.SettlementService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/settlement")
public class SettlementController {
    private final SettlementService settlementService;

    SettlementController(SettlementService settlementService){
        this.settlementService=settlementService;
    }

    @PostMapping("/{packetHash}")
    public SettlementResponse settlePayment(@PathVariable String packetHash) {
        return settlementService.settlePayment(packetHash);
    }
    
}
