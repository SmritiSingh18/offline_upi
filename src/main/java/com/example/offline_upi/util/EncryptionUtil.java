package com.example.offline_upi.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {
    private static final String SECRET_KEY="1234567890123456";
    private static final String ALGORITHM="AES";

    public String encrypt(String data){
        try{
            SecretKeySpec key=new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);

            Cipher cipher=Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE,key);

            byte[] encryptedBytes=cipher.doFinal(data.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);
        }catch(Exception e){
            throw new RuntimeException("Encryption failed");
        }

    }
    public String decrypt(String encryptedData){
        try {
            SecretKeySpec key=new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher=Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE,key);

           byte[] decoded=Base64.getDecoder().decode(encryptedData);

           byte[] original=cipher.doFinal(decoded);

           return new String(original);

            
        } catch (Exception e) {
            throw new RuntimeException("Decryption Failed");
    }

}
}
