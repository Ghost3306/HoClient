package com.example.client;
import org.json.simple.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encrypt {
        public static void main(String[] args) throws Exception {
            JSONObject json = new JSONObject();
            json.put("name", "Alice");
            json.put("age", 30);
            String jsonString = json.toString();

            String key = "0123456789abcdef"; // 128-bit key
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] ciphertextBytes = cipher.doFinal(jsonString.getBytes());
            String encodedCiphertext = Base64.getEncoder().encodeToString(ciphertextBytes);
            System.out.println("Encrypted JSON: " + encodedCiphertext);
        }
    }
