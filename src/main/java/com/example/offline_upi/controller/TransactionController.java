package com.example.offline_upi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.offline_upi.dto.TransactionResponse;
import com.example.offline_upi.dto.TransferMoneyRequest;
import com.example.offline_upi.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    TransactionController(TransactionService transactionService){
        this.transactionService=transactionService;
    }
    @PostMapping("/transfer")
    public TransactionResponse transferMoney(@RequestBody TransferMoneyRequest request) {
        
        return transactionService.transferMoney(request);
    }
    
}
