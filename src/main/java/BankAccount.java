public class BankAccount {
    // Constants
    static final double INITIAL_BALANCE = 1_000;

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
}