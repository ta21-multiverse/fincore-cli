package io.github.ta21multiverse.fincorecli;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Performs actions on a io.github.ta21multiverse.fincorecli.BankAccount.
 * Handles the user input and other tasks that would be less appropriate to be on the io.github.ta21multiverse.fincorecli.BankAccount class.
 */
public class AccountManager {
    // Scanner for use by all instances so they don't need to create a new one each time
    private static final Scanner scanner = new Scanner(System.in);

    // The account to perform actions on
    private final BankAccount account;

    public AccountManager(BankAccount account) {
        this.account = account;
    }

    public void depositAction() {
        // Attempt to read the user's choice,
        // handling the possibility that they did not input the correct data type.
        double depositAmount;
        try {
            System.out.print("Enter amount to deposit: $");
            depositAmount = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Invalid deposit amount");

            // Unlike in `showMainMenu`, user input is not looped until they enter a valid number.
            // This is because:
            // 1) It is quick to get back to this input should they have mistyped;
            // 2) The user can this way deliberately enter an invalid amount to abort the deposit.
            return;
        }

        // Attempt to deposit into account
        try {
            this.account.deposit(depositAmount);
        } catch (IllegalArgumentException e) {
            // Show error message
            System.out.println(e.getMessage());

            // Do not continue if there was an error
            return;
        }

        // Show completion message
        System.out.println("Deposit successful.");
        System.out.printf("Amount deposited: $%.2f\n", depositAmount);
        System.out.printf("New balance: $%.2f\n", this.account.getBalance());
    }

    public void withdrawAction() {
        // Attempt to read the user's choice,
        // handling the possibility that they did not input the correct data type.
        double withdrawAmount;
        try {
            System.out.print("Enter amount to withdraw: $");
            // REFACTOR OUT SCANNER
            withdrawAmount = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Invalid withdraw amount");

            // Unlike in `showMainMenu`, user input is not looped until they enter a valid number.
            // This is because:
            // 1) It is quick to get back to this input should they have mistyped;
            // 2) The user can this way deliberately enter an invalid amount to abort the deposit.
            return;
        }

        // Attempt to withdraw from account
        try {
            this.account.withdraw(withdrawAmount);
        } catch (IllegalArgumentException e) {
            // Show error message
            System.out.println(e.getMessage());

            // Do not continue if there was an error
            return;
        }

        // Show completion message
        System.out.println("Withdrawal successful.");
        System.out.printf("Amount withdrawn: $%.2f\n", withdrawAmount);
        System.out.printf("New balance: $%.2f\n", this.account.getBalance());
    }

    public void checkBalanceAction() {
        // Get balance from account
        final double balance = this.account.getBalance();

        // Display balance
        System.out.printf("Your current balance: $%,.2f\n", balance);
    }
}
