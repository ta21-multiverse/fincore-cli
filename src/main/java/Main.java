final double SUSPICIOUS_DEPOSIT_AMOUNT = 10_000;
final Scanner scanner = new Scanner(System.in);
double balance = 1_000;

void main() {
    showWelcomeMessage();

    final byte choice = showMainMenu();

    // Only the cases below can be returned from `showMainMenu`.
    // Validation, including error handling, is processed within said method
    switch (choice) {
        case 1 -> performDepositAction();
        case 2 -> performWithdrawAction();
        case 3 -> performCheckBalanceAction();
        default -> {}  // Nothing yet. Carrying on will exit the program
    }
}

void showWelcomeMessage() {
    System.out.println("Welcome to FinCore CLI Banking!");
    System.out.println("Account Holder: Alex Doe");
    System.out.printf("Initial Balance: $%.2f\n", balance);
}

byte showMainMenu() {
    // Show the menu
    System.out.println("=== FinCore CLI Banking Menu ===");
    System.out.println("1. Deposit");
    System.out.println("2. Withdraw");
    System.out.println("3. Check Balance");
    System.out.println("4. Exit");

    // The user's input is enclosed in a loop so that if the user inputs incorrectly,
    // then they can keep trying until they enter a valid action.
    // It is assigned an initial value of 0 to ensure that it is initialized.
    byte chosenAction = 0;
    boolean userInputIsValid = false;
    while (!userInputIsValid) {
        // Use `print` instead of `println` for the final line so that
        // the user's input is on the same line (after the colon)
        System.out.print("Please select an option (1-4): ");

        // Attempt to read the user's choice,
        // handling the possibility that they did not input the correct data type.
        try {
            chosenAction = scanner.nextByte();
        } catch (InputMismatchException e) {  // If the user's input does not match the Integer regular expression
            System.out.println("Invalid action; must be a number.");
            continue;
        }

        // Validate that the action entered by the user is within the accepted range
        final boolean actionTooLow = chosenAction < 1;
        final boolean actionTooHigh = chosenAction > 4;
        if (actionTooLow || actionTooHigh) {
            System.out.println("Invalid action; must be between 1 and 4.");
            continue;
        }

        // If this point is reached then all checks have passed, so the user has entered a valid action
        userInputIsValid = true;
    }

    // `chosenAction` now contains a valid action between 1 and 4
    return chosenAction;
}

void performDepositAction() {
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

    // Ensure the user does not deposit more money than is suspicious
    // (for fraud detection purposes)
    if (depositAmount > SUSPICIOUS_DEPOSIT_AMOUNT) {
        System.out.printf("You cannot deposit more than $%.2f using this program", SUSPICIOUS_DEPOSIT_AMOUNT);
        System.out.println("Please come into one of our branches in person");

        // User input is not looped (see comment above previous `return` statement)
        return;
    }

    // Perform deposit
    balance += depositAmount;

    System.out.println("Deposit successful.");
    System.out.printf("Amount deposited: $%.2f\n", depositAmount);
    System.out.printf("New balance: $%.2f\n", balance);
}

void performWithdrawAction() {
    // Attempt to read the user's choice,
    // handling the possibility that they did not input the correct data type.
    double withdrawAmount;
    try {
        System.out.print("Enter amount to withdraw: $");
        withdrawAmount = scanner.nextDouble();
    } catch (InputMismatchException e) {
        System.out.println("Invalid withdraw amount");

        // Unlike in `showMainMenu`, user input is not looped until they enter a valid number.
        // This is because:
        // 1) It is quick to get back to this input should they have mistyped;
        // 2) The user can this way deliberately enter an invalid amount to abort the deposit.
        return;
    }

    // Ensure the user does not withdraw more money than they have
    if (withdrawAmount > balance) {
        System.out.println("You cannot withdraw more money than you have");
        System.out.println("Consider enabling overdraft for your account");

        // User input is not looped (see comment above previous `return` statement)
        return;
    }

    // Perform withdraw
    balance -= withdrawAmount;

    System.out.println("Withdrawal successful.");
    System.out.printf("Amount withdrawn: $%.2f\n", withdrawAmount);
    System.out.printf("New balance: $%.2f\n", balance);
}

void performCheckBalanceAction() {
    System.out.printf("Your current balance: $%.2f\n", balance);
}
