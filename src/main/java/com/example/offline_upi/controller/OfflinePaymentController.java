package com.example.offline_upi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.offline_upi.dto.OfflinePaymentRequest;
import com.example.offline_upi.dto.OfflinePaymentResponse;
import com.example.offline_upi.service.OfflinePaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/offline")
public class OfflinePaymentController {
    private final OfflinePaymentService offlinePaymentService;

     public OfflinePaymentController(OfflinePaymentService offlinePaymentService){
        this.offlinePaymentService=offlinePaymentService;
    }

    @PostMapping("/pay")
    public OfflinePaymentResponse createOfflinePayment(@RequestBody OfflinePaymentRequest request)  throws Exception{
        return offlinePaymentService.createOfflinePayment(request);
    }
    
}
