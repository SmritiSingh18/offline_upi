package com.example.offline_upi.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.stereotype.Component;

@Component
public class RSAUtil {
    private static final String ALGORITHM="RSA";
    public KeyPair generateKeyPair()
    throws Exception{
        KeyPairGenerator generator=KeyPairGenerator.getInstance(ALGORITHM);

        generator.initialize(2048);
        return generator.generateKeyPair();
    }
    public String encrypt(byte[] data,PublicKey publicKey)
    throws Exception{
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted=cipher.doFinal(data);
        return Base64.getEncoder().encodeToString(encrypted);

    }
    public byte[] decrypt(String encryptedData,PrivateKey privateKey)
    throws Exception{
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decoded=Base64.getDecoder().decode(encryptedData);
        return cipher.doFinal(decoded);
    }
    public String encodePublicKey(PublicKey publicKey){
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public String encodePrivateKey(PrivateKey privateKey){
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public PublicKey decodePublicKey(String key) throws Exception{
        byte[] decoded=Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec=new X509EncodedKeySpec(decoded);
        KeyFactory factory=KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    public PrivateKey decodePrivateKey(String key) throws Exception{
        byte[] decoded=Base64.getDecoder().decode(key);
        PKCS8EncodedKeySpec spec=new PKCS8EncodedKeySpec(decoded);
        KeyFactory factory=KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }
}
