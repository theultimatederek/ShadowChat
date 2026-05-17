package com.example.shadowchat;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionHelper {

    private static final String SECRET_KEY = "1234567890123456";

    public static String encrypt(String message) {

        try {

            SecretKeySpec key =
                    new SecretKeySpec(
                            SECRET_KEY.getBytes(),
                            "AES"
                    );

            Cipher cipher =
                    Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encrypted =
                    cipher.doFinal(
                            message.getBytes(StandardCharsets.UTF_8)
                    );

            return Base64.encodeToString(
                    encrypted,
                    Base64.DEFAULT
            );

        } catch (Exception e) {

            e.printStackTrace();

            return "Encryption Error";
        }
    }

    public static String decrypt(String encryptedMessage) {

        try {

            SecretKeySpec key =
                    new SecretKeySpec(
                            SECRET_KEY.getBytes(),
                            "AES"
                    );

            Cipher cipher =
                    Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decoded =
                    Base64.decode(
                            encryptedMessage,
                            Base64.DEFAULT
                    );

            byte[] decrypted =
                    cipher.doFinal(decoded);

            return new String(
                    decrypted,
                    StandardCharsets.UTF_8
            );

        } catch (Exception e) {

            e.printStackTrace();

            return "Decryption Error";
        }
    }
}
