package com.assetmanager.util.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;


public class PasswordEncoder implements PasswordEncoderI {

    @Override
    public boolean verifyPassword(String rawPassword, String encodedPassword) throws NoSuchAlgorithmException {

        if (rawPassword == null) {
            throw new IllegalArgumentException("Raw Password cannot be null");
        }
        if (encodedPassword == null || encodedPassword.isEmpty()) {
            System.out.println("Empty encoded password");
            return false;
        }
        return (encodePassword(rawPassword).equals(encodedPassword));
    }

    // TODO - Password Encoder using custom hashing
    @Override
    public String encodePassword(String rawPassword) throws NoSuchAlgorithmException {
        if (rawPassword == null || rawPassword.isEmpty())
            throw new IllegalArgumentException("Raw Password cannot be null or Empty");

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] message = rawPassword.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(message);
        byte[] passWordDigest = messageDigest.digest();
        return bytesToString(passWordDigest);
    }

    @Override
    public String bytesToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}