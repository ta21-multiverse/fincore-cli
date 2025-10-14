public class Main {
    // Menu declarations
    // These are constants in a way, as they are configured here to use for all times the program is run
    static final Menu mainMenu = new Menu("FinCore CLI", new String[]{
            "Deposit",
            "Withdraw",
            "Check Balance",
            "Exit",
    });

    // Stores the account currently being worked on
    static BankAccount currentWorkingAccount;

    // Entry point
    static void main() {
        // Set up account manager to perform actions on a bank account
        currentWorkingAccount = new BankAccount(
                new User("Alex Doe", "123", "alex.doe@example.com")
        );
        final var currentAccountManager = new AccountManager(currentWorkingAccount);

        showWelcomeMessage();

        // Process actions from the user until they wish to quit
        boolean applicationShouldQuit = false;
        while (!applicationShouldQuit) {
            final byte choice = mainMenu.show();

            // Only the cases below can be returned from `showMainMenu`.
            // Validation, including error handling, is processed within said method
            switch (choice) {
                case 1 -> currentAccountManager.depositAction();
                case 2 -> currentAccountManager.withdrawAction();
                case 3 -> currentAccountManager.checkBalanceAction();
                default -> applicationShouldQuit = true;
            }
        }
    }

    static void showWelcomeMessage() {
        System.out.println("Welcome to FinCore CLI Banking!");
        System.out.println("Account Holder: " + currentWorkingAccount.getAccountHolder().getName());
        System.out.printf("Initial Balance: $%.2f\n", currentWorkingAccount.getBalance());
    }
}