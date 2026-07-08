package com.example.offline_upi.util;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import org.springframework.stereotype.Component;

@Component
public class AESUtil {
    private static final String ALGORITHM="AES/GCM/NoPadding";
    private static final int KEY_SIZE=256;
    private static final int IV_SIZE=12;
    private static final int TAG_LENGTH=128;

    public SecretKey generateKey()
    throws Exception{
        KeyGenerator keyGenerator=KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE);
        return keyGenerator.generateKey();
    }

    public byte[] generateIV(){
        byte[] iv=new byte[IV_SIZE];
        SecureRandom random=new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }
    public String encrypt(
        String plainText,
        SecretKey secretKey,
        byte[] iv
    )throws Exception{
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        GCMParameterSpec spec=new GCMParameterSpec(TAG_LENGTH, iv);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,spec);
        byte[] encryptedBytes=cipher.doFinal(plainText.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String cipherText,SecretKey secretKey,byte[] iv)
    throws Exception{
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        GCMParameterSpec spec=new GCMParameterSpec(TAG_LENGTH, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey,spec);
        byte[] decoded=Base64.getDecoder().decode(cipherText);
        byte[] decrypted=cipher.doFinal(decoded);

        return new String(decrypted);
    }
}
