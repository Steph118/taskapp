package com.steph18.demo.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtKeyGenerator {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Bean
    public CommandLineRunner generateJwtSecret() {
        return args -> {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256"); // HS256
            keyGen.init(256); // génère une clé de 256 bits
            SecretKey secretKey = keyGen.generateKey();
            String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            System.out.println("Clé JWT HS256 sécurisée : " + base64Key);
        };
    }

   }
