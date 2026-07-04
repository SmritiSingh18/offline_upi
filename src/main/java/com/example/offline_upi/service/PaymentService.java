package com.example.offline_upi.service;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.TransferRequest;
import com.example.offline_upi.dto.TransferResponse;
import com.example.offline_upi.entity.Wallet;
import com.example.offline_upi.repository.TransactionRepository;
import com.example.offline_upi.repository.WalletRepository;

@Service
public class PaymentService {
    
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    PaymentService(TransactionRepository transactionRepository,WalletRepository walletRepository){
        this.transactionRepository=transactionRepository;
        this.walletRepository=walletRepository;
    }
  public TransferResponse tranferMoney(TransferRequest request){
    Wallet senderWallet=walletRepository
                        .findByWalletNumber(request.getSenderWalletNumber())
                        .orElseThrow(()->
                                 new RuntimeException("Sender Wallet not found")
                    );

    Wallet receiverWallet=walletRepository
                          .findByWalletNumber(request.getReceiverWalletNumber())
                          .orElseThrow(() ->
                             new RuntimeException("Receiver Wallet not found")
                        );

    if(senderWallet.getBalance().compareTo(request.getAmount())<0){
        throw new RuntimeException("Insufficient Balance");
    }
  }


}
