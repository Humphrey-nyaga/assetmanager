package com.assetmanager.util.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PasswordEncoderTest {
    @InjectMocks
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        openMocks(this);

    }

    @Test
    void verifyPassword() throws NoSuchAlgorithmException {
        assertEquals(passwordEncoder.encodePassword("hello"), passwordEncoder.encodePassword("hello"));
    }

    @Test
    void differentPasswordsFail() throws NoSuchAlgorithmException {
        Assertions.assertFalse(passwordEncoder.encodePassword("hello").equals(passwordEncoder.encodePassword("world")));
    }

}