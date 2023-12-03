package com.assetmanager.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.openMocks;


class EmailValidatorTest {
    @InjectMocks
    EmailValidator emailValidator;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

    @Test
    void testEmailWithoutDotComTest() {
        Assertions.assertFalse(emailValidator.isValidEmail("email@examplewithoutdotcom"));
    }

    @Test
    void invalidEmailsTest() {
        Assertions.assertFalse(emailValidator.isValidEmail("emailexample.com"));
        Assertions.assertFalse(emailValidator.isValidEmail("email@example@google.com"));
        Assertions.assertFalse(emailValidator.isValidEmail("email2..well@example.com"));
        Assertions.assertFalse(emailValidator.isValidEmail("just\"not\"right@example.com"));

    }

    @Test
    void validEmailTest() {
        Assertions.assertTrue(emailValidator.isValidEmail("john.doe@example.com"));
        Assertions.assertTrue(emailValidator.isValidEmail("jane_smith@example.co.uk"));
        Assertions.assertTrue(emailValidator.isValidEmail("info@company.org"));
        Assertions.assertTrue(emailValidator.isValidEmail("info2@example.org"));
        Assertions.assertTrue(emailValidator.isValidEmail("info2@my-example.org"));

    }
}