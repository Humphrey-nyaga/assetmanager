package com.assetmanager.util.security;

import java.security.NoSuchAlgorithmException;

public interface PasswordEncoderI {
    String encodePassword(String rawPassword) throws NoSuchAlgorithmException;
    boolean verifyPassword(String rawPassword, String encodedPassword) throws NoSuchAlgorithmException;

}
