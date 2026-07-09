package com.example.offline_upi.service;

import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.OfflinePaymentRequest;
import com.example.offline_upi.dto.OfflinePaymentResponse;
import com.example.offline_upi.entity.OfflinePaymentPacket;
import com.example.offline_upi.entity.Wallet;
import com.example.offline_upi.repository.OfflinePaymentPacketRepository;
import com.example.offline_upi.repository.WalletRepository;
import com.example.offline_upi.util.AESUtil;
import com.example.offline_upi.util.PacketHashUtil;
import com.example.offline_upi.util.RSAUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OfflinePaymentService {
    private final OfflinePaymentPacketRepository packetRepository;
    private final AESUtil aesUtil;
    private final RSAUtil rsaUtil;
    private final PacketHashUtil packetHashUtil;
    private final WalletRepository walletRepository;

    OfflinePaymentService(OfflinePaymentPacketRepository packetRepository,AESUtil aesUtil,RSAUtil rsaUtil,PacketHashUtil packetHashUtil,WalletRepository walletRepository){
       this.packetRepository=packetRepository;
       this.aesUtil=aesUtil;
       this.rsaUtil=rsaUtil;
       this.packetHashUtil=packetHashUtil;
       this.walletRepository=walletRepository;
    }
    public OfflinePaymentResponse createOfflinePayment(OfflinePaymentRequest request)throws Exception{
        ObjectMapper mapper=new ObjectMapper();
        String paymentJson=mapper.writeValueAsString(request);

        SecretKey aesKey=aesUtil.generateKey();
        byte[] iv=aesUtil.generateIV();
        String cipherText=aesUtil.encrypt(paymentJson, aesKey, iv);
        Wallet receiverWallet=walletRepository.findByWalletNumber(request.getReceiverWalletNumber())
                              .orElseThrow(
                                ()-> new RuntimeException("Receiver wallet not found")
                            );
        PublicKey receiverPublicKey=rsaUtil.decodePublicKey(receiverWallet.getUser().getPublicKey());
        String encryptedKey=rsaUtil.encrypt(aesKey.getEncoded(), receiverPublicKey);
        String packetHash=packetHashUtil.generateHash(cipherText+encryptedKey);
        OfflinePaymentPacket packet=OfflinePaymentPacket.builder()
                                    .packetHash(packetHash)
                                    .receiverWalletNumber(request.getReceiverWalletNumber())
                                    .encryptedKey(encryptedKey)
                                    .iv(Base64.getEncoder().encodeToString(iv))
                                    .cipherText(cipherText)
                                    .synced(false)
                                    .build();
                                packetRepository.save(packet);

                                return OfflinePaymentResponse.builder()
                                       .packetHash(packetHash)
                                       .message("Offline Payment Packet Generated successfully")
                                       .build();
    }
}
    