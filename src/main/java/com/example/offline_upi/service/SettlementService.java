package com.example.offline_upi.service;

import java.time.LocalDateTime;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.OfflinePaymentRequest;
import com.example.offline_upi.dto.SettlementResponse;
import com.example.offline_upi.entity.OfflinePaymentPacket;
import com.example.offline_upi.entity.Transaction;
import com.example.offline_upi.entity.Wallet;
import com.example.offline_upi.enums.TransactionStatus;
import com.example.offline_upi.repository.OfflinePaymentPacketRepository;
import com.example.offline_upi.repository.TransactionRepository;
import com.example.offline_upi.repository.WalletRepository;
import com.example.offline_upi.util.AESUtil;
import com.example.offline_upi.util.RSAUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class SettlementService {
    private final OfflinePaymentPacketRepository packetRepository;
    private final WalletRepository walletRepository;
    private final AESUtil aesUtil;
    private final RSAUtil rsaUtil; 
    private final TransactionRepository transactionRepository;
    SettlementService(OfflinePaymentPacketRepository packetRepository,WalletRepository walletRepository,AESUtil aesUtil,RSAUtil rsaUtil,TransactionRepository transactionRepository){
        this.packetRepository=packetRepository;
        this.walletRepository=walletRepository;
        this.aesUtil=aesUtil;
        this.rsaUtil=rsaUtil;
        this.transactionRepository=transactionRepository;
    }

    @Transactional
    public SettlementResponse settlePayment(String packetHash) throws Exception{
        OfflinePaymentPacket packet=packetRepository.findByPacketHash(packetHash)
                                    .orElseThrow(
                                        () -> new RuntimeException("Packet not found")
                                    );
        if (packet.isSynced()) {
            return SettlementResponse.builder()
                                      .packetHash(packetHash)
                                      .status("FAILED")
                                      .message("Packet already settled")
                                      .build();
            
        }

        Wallet receiverWallet=walletRepository.findByWalletNumber(packet.getReceiverWalletNumber())
                              .orElseThrow(
                                ()-> new RuntimeException("Receiver wallet not found")
                              );

        byte[] aesBytes=rsaUtil.decrypt(packet.getEncryptedKey(), rsaUtil.decodePrivateKey(receiverWallet.getUser().getPrivateKey()));
        SecretKey aesKey=aesUtil.getKeyFromBytes(aesBytes);

        byte[] iv=Base64.getDecoder().decode(packet.getIv());
        String json=aesUtil.decrypt(packet.getCipherText(), aesKey, iv);

        ObjectMapper mapper=new ObjectMapper();

        OfflinePaymentRequest payment=mapper.readValue(json,OfflinePaymentRequest.class );

        Wallet senderWallet=walletRepository.findByWalletNumber(payment.getSenderWalletNumber())
                            .orElseThrow(
                                ()-> new RuntimeException("Sender wallet not found")
                            );
        senderWallet.setBalance(senderWallet.getBalance().subtract(payment.getAmount()));

        receiverWallet.setBalance(receiverWallet.getBalance().add(payment.getAmount()));

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);
        
        Transaction transaction=Transaction.builder()
                                .senderWalletNumber(payment.getSenderWalletNumber())
                                .receiverWalletNumber(payment.getReceiverWalletNumber())
                                .packetId(packetHash)
                                .amount(payment.getAmount())
                                .status(TransactionStatus.SUCCESS)
                                .transactionTime(LocalDateTime.now())
                                .syncedAt(LocalDateTime.now())
                                .build();
        transactionRepository.save(transaction);
        packet.setSynced(true);
        packetRepository.save(packet);
    
        return SettlementResponse.builder()
                                .packetHash(packetHash)
                                .status("SUCCESS")
                                .message("Offline payment settled successfully")
                                .build();
    }
    
}
