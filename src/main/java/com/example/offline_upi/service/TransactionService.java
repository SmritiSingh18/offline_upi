package com.example.offline_upi.service;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.TransactionResponse;
import com.example.offline_upi.dto.TransferMoneyRequest;
import com.example.offline_upi.entity.Transaction;
import com.example.offline_upi.entity.Wallet;
import com.example.offline_upi.enums.TransactionStatus;
import com.example.offline_upi.repository.TransactionRepository;
import com.example.offline_upi.repository.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    TransactionService(WalletRepository walletRepository,TransactionRepository transactionRepository){
        this.walletRepository=walletRepository;
        this.transactionRepository=transactionRepository;
    }
    @Transactional
    public TransactionResponse transferMoney(TransferMoneyRequest request){
        Wallet sender=walletRepository.findByWalletNumber(request.getSenderWalletNumber())
                      .orElseThrow(
                        ()-> new RuntimeException("Sender Wallet not found")
                      );
        Wallet receiver=walletRepository.findByWalletNumber(request.getReceiverWalletNumber())
                        .orElseThrow(
                            ()-> new RuntimeException("Receiver wallet not found")
                        );
        
        if(sender.getBalance().compareTo(request.getAmount())<0){
            throw new RuntimeException("Insufficient Balance");
        }

        sender.setBalance(sender.getBalance().subtract(request.getAmount()));
        receiver.setBalance(receiver.getBalance().add(request.getAmount()));

        walletRepository.save(sender);
        walletRepository.save(receiver);

       Transaction transaction=Transaction.builder().senderWalletNumber(sender.getWalletNumber())
                                                    .receiverWalletNumber(receiver.getWalletNumber())
                                                    .amount(request.getAmount())
                                                    .status(TransactionStatus.SUCCESS)
                                                    .build();
        Transaction save=transactionRepository.save(transaction);
        
        return TransactionResponse.builder()
                .transactionId(save.getId())
                .senderWalletNumber(save.getSenderWalletNumber())
                .receiverWalletNumber(save.getReceiverWalletNumber())
                .amount(save.getAmount())
                .status(save.getStatus())
                .createdAt(save.getCreatedAt())
                .build();
}
}
