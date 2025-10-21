package io.github.ta21multiverse.fincorecli.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Reusable component for showing CLI menus
 */
public class Menu {
    // Scanner for use by all instances so they don't need to create a new one each time
    private static final Scanner scanner = new Scanner(System.in);

    // io.github.ta21multiverse.fincorecli.ui.Menu Configuration Fields
    // (marked `final` because they only need to be set once)
    private final String name;
    private final String[] choices;

    public Menu(String name, String[] choices) {
        this.name = name;
        this.choices = choices;
    }

    public byte show() {
        // Show header
        System.out.println("===" + this.name + "===");

        // Show menu choices
        for (int choiceIndex = 0; choiceIndex < this.choices.length; choiceIndex++) {
            final String choiceText = this.choices[choiceIndex];
            System.out.println(choiceIndex + 1 + ". " + choiceText);
        }

        // The user's input is enclosed in a loop so that if the user inputs incorrectly,
        // then they can keep trying until they enter a valid action.
        // It is assigned an initial value of 0 to ensure that it is initialized.
        byte chosenAction = 0;
        boolean userInputIsValid = false;
        while (!userInputIsValid) {
            // Use `print` instead of `println` for the final line so that
            // the user's input is on the same line (after the colon)
            System.out.print("Please select an option (1-" + this.choices.length + "): ");

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
                System.out.println("Invalid action; must be between 1 and " + this.choices.length + ".");
                continue;
            }

            // If this point is reached then all checks have passed, so the user has entered a valid action
            userInputIsValid = true;
        }

        // `chosenAction` now contains a valid action between 1 and 4
        return chosenAction;
    }
}
