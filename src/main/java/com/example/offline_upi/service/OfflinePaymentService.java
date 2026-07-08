package com.example.offline_upi.service;

import java.security.KeyPair;
import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.example.offline_upi.dto.OfflinePaymentRequest;
import com.example.offline_upi.dto.OfflinePaymentResponse;
import com.example.offline_upi.entity.OfflinePaymentPacket;
import com.example.offline_upi.repository.OfflinePaymentPacketRepository;
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

    OfflinePaymentService(OfflinePaymentPacketRepository packetRepository,AESUtil aesUtil,RSAUtil rsaUtil,PacketHashUtil packetHashUtil){
       this.packetRepository=packetRepository;
       this.aesUtil=aesUtil;
       this.rsaUtil=rsaUtil;
       this.packetHashUtil=packetHashUtil;
    }
    public OfflinePaymentResponse createOfflinePayment(OfflinePaymentRequest request)throws Exception{
        ObjectMapper mapper=new ObjectMapper();
        String paymentJson=mapper.writeValueAsString(request);

        SecretKey aesKey=aesUtil.generateKey();
        byte[] iv=aesUtil.generateIV();
        String cipherText=aesUtil.encrypt(paymentJson, aesKey, iv);
        KeyPair keyPair=rsaUtil.generateKeyPair();
        String encryptedKey=rsaUtil.encrypt(aesKey.getEncoded(),keyPair.getPublic());
        String packetHash=packetHashUtil.generateHash(cipherText+encryptedKey);
        OfflinePaymentPacket packet=OfflinePaymentPacket.builder()
                                    .packetHash(packetHash)
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
    