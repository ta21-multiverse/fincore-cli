package io.github.ta21multiverse.fincorecli.model;

public class BankAccount {
    // Constants
    static final double INITIAL_BALANCE = 1_000;
    static final double SUSPICIOUS_DEPOSIT_AMOUNT = 10_000;

    // Fields
    private double balance;
    private User accountHolder;

    // Constructor
    public BankAccount(User accountHolder) {
        this.accountHolder = accountHolder;

        // With the default FinCore CLI plan, you get an initial balance of $1,000.
        // How generous!
        this.balance = INITIAL_BALANCE;
    }

    // Getters and setters
    public double getBalance() {
        return balance;
    }

    public User getAccountHolder() {
        return accountHolder;
    }

    // Balance manipulation methods
    void deposit(double amount) throws IllegalArgumentException {
        // Ensure the user does not deposit more money than is suspicious
        // (for fraud detection purposes)
        if (amount > SUSPICIOUS_DEPOSIT_AMOUNT) {
            final String errorMessage = String.format(
                    "You cannot deposit more than $%.2f using this program\n" +
                            "Please come into one of our branches in person",
                    SUSPICIOUS_DEPOSIT_AMOUNT
            );
            throw new IllegalArgumentException(errorMessage);
        }

        // Perform deposit
        balance += amount;
    }

    void withdraw(double amount) throws IllegalArgumentException {
        // Ensure the user does not withdraw more money than they have
        if (amount > balance) {
            final String errorMessage = "You cannot withdraw more money than you have\n" +
                    "Consider enabling overdraft for your account";

            throw new IllegalArgumentException(errorMessage);
        }

        // Perform withdraw
        balance -= amount;
    }
}