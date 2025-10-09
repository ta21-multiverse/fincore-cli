void main() {
    showWelcomeMessage();

    final byte choice = showMainMenu();
    IO.println("YOU CHOSE: " + choice);
}

void showWelcomeMessage() {
    System.out.println("Welcome to FinCore CLI Banking!");
    System.out.println("Account Holder: Alex Doe");
    System.out.println("Initial Balance: $1000");
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
            var scanner = new Scanner(System.in);
            chosenAction = scanner.nextByte();
        } catch (InputMismatchException e) {  // If the user's input does not match the Integer regular expression
            System.err.println("Invalid action; must be a number.");
        }

        // Validate that the action entered by the user is within the accepted range
        final boolean actionTooLow = chosenAction < 1;
        final boolean actionTooHigh = chosenAction > 4;
        if (actionTooLow || actionTooHigh) {
            // Action was invalid
            System.err.println("Invalid action; must be between 1 and 4.");
        } else {
            // Action was valid
            userInputIsValid = true;
        }
    }

    // `chosenAction` now contains a valid action between 1 and 4
    return chosenAction;
}
