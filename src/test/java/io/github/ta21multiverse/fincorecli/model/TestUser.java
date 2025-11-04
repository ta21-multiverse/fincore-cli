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
}
