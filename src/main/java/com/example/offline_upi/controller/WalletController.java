package com.example.offline_upi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.offline_upi.dto.AddMoneyRequest;
import com.example.offline_upi.dto.WalletResponse;
import com.example.offline_upi.service.WalletService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService service;
    WalletController(WalletService service){
        this.service=service;
    }
    @GetMapping("/{phoneNumber}")
    public WalletResponse getWallet(@PathVariable String phoneNumber) {
        return service.getWalletDetails(phoneNumber);
    }
    @PutMapping("/add-money")
    public WalletResponse addMoney(@RequestBody AddMoneyRequest  request) {
          return service.addMoney(request);
    }
    
}
