package io.github.ta21multiverse.fincorecli.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestUser {
    private User user;

    @BeforeEach
    void setUpTestUserInstance() {
        user = new User("User", "user@example.com", "01632 960 001");
    }


    @Test
    @DisplayName("setEmailAddress: Valid email")
    void testValidEmail() {
        user.setEmailAddress("bond@example.com");
        assertEquals("bond@example.com", user.getEmailAddress());
    }

    @Test
    @DisplayName("setEmailAddress: Valid email where there is a dot before the domain")
    void testValidEmailWithDot() {
        user.setEmailAddress("james.bond@example.com");
        assertEquals("james.bond@example.com", user.getEmailAddress());
    }

    @Test
    @DisplayName("setEmailAddress: Invalid email (no domain)")
    void testEmailWithNoDomain() {
        assertThrows(IllegalArgumentException.class, () -> user.setEmailAddress("bondexample.com"));
    }

    @Test
    @DisplayName("setEmailAddress: Invalid email (no dot in domain)")
    void testEmailWithNoDotInDomain() {
        assertThrows(IllegalArgumentException.class, () -> user.setEmailAddress("bond@examplecom"));
    }

    @Test
    @DisplayName("setEmailAddress: Invalid email (empty string)")
    void testEmptyEmail() {
        assertThrows(IllegalArgumentException.class, () -> user.setEmailAddress(""));
    }


    @Test
    @DisplayName("setPhoneNumber: Valid number (international format)")
    void testValidNumberInternationalFormat() {
        user.setPhoneNumber("+442071234567");
        assertEquals("+442071234567", user.getPhoneNumber());
    }

    @Test
    @DisplayName("setPhoneNumber: Valid number (domestic format)")
    void testValidNumberDomesticFormat() {
        user.setPhoneNumber("01632960001");
        assertEquals("01632960001", user.getPhoneNumber());
    }

    @Test
    @DisplayName("setPhoneNumber: Valid number (domestic format with spaces)")
    void testValidNumberDomesticFormatWithSpaces() {
        user.setPhoneNumber("020 7123 4567");
        assertEquals("02071234567", user.getPhoneNumber());
    }

    @Test
    @DisplayName("setPhoneNumber: Invalid number (non-digit characters)")
    void testInvalidNumber() {
        assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber("-442071234567"));
    }

    @Test
    @DisplayName("setPhoneNumber: Invalid number (empty string)")
    void testEmptyNumber() {
        assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(""));
    }
}
