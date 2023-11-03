package com.assetmanager.util.security;

public interface PasswordEncoderI {
    String encodePassword(String rawPassword);
    boolean verifyPassword(String rawPassword, String encodedPassword);

}
