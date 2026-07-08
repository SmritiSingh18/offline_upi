package com.example.offline_upi.util;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;

@Component
public class PacketHashUtil {
    private static final String ALGORITHM="SHA-256";

    public String generateHash(String data)
    throws Exception{
        MessageDigest digest=MessageDigest.getInstance(ALGORITHM);
        byte[] hashBytes=digest.digest(data.getBytes());
        StringBuilder hexString=new StringBuilder();

        for(byte b:hashBytes){
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
