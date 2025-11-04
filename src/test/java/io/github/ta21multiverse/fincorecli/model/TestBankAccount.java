package io.github.ta21multiverse.fincorecli.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestBankAccount {
    private BankAccount account;

    @BeforeEach
    void setUpTestBankAccountInstance() {
        account = new BankAccount(new User("User", "user@example.com", "01632 960 001"));
    }

    @Test
    @DisplayName("deposit: Negative amount should be invalid")
    void testNegativeDeposit() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-20));
    }

    @Test
    @DisplayName("deposit: Lower boundary deposit amount should be valid")
    void testZeroDeposit() {
        account.deposit(0);
        assertEquals(BankAccount.INITIAL_BALANCE, account.getBalance());
    }
    
    @Test
    @DisplayName("deposit: Valid deposit amount")
    void testValidDeposit() {
        account.deposit(100);
        assertEquals(BankAccount.INITIAL_BALANCE + 100, account.getBalance());
    }

    @Test
    @DisplayName("deposit: Upper boundary deposit amount should be invalid")
    void testUpperBoundDeposit() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(BankAccount.SUSPICIOUS_DEPOSIT_AMOUNT));
    }

    @Test
    @DisplayName("deposit: Invalid deposit amount (too high)")
    void testInvalidDeposit() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(BankAccount.SUSPICIOUS_DEPOSIT_AMOUNT + 100));
    }
}
