package com.steph18.demo.utils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppUtils {

    public static String generateJwtSecret()  {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256); // génère une clé de 256 bits
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(AppUtils.class.getName())
                    .log(Level.SEVERE, "NoSuchAlgorithmException : ", e);
            return null;
        }
    }
}
