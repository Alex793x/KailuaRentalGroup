package utility;

import java.time.LocalDate;
import java.util.Scanner;


/**
 * <p>The {@code UI} class will be handling all inputs from user, the purpose is to make sure
 * that all processed inputs from user, always goes through the same class, minimizing the search pair when ever
 * an error occurs from the {@code Scanner} class </p>
 * <br>
 * @implNote {@link #readInteger()} A simple method, always validating returned input is of Integer type <br>
 * {@link #readLine()} A simple method always ensuring the returned line only contains letters, and no Integers <br>
 * {@link #readNext()} A simple method always ensuring the returned input contains one word, with no Integers <br>
 * {@link #readBoolean()} A simple method always ensuring the input returns a boolean type <br>
 * {@link #readDouble()} A simple method always ensuring the input returns a double type <br>
 * {@link #readDate()} A simple method always ensures the input returns a valid Date within a specific period.
 */
public class UI {

    public Scanner input;


    // Constructor -----------------------------------------------------------------

    /**
     * The UI constructor
     */
    public UI() {
        input = new Scanner(System.in);
    }

    // Methods ---------------------------------------------------------------------

    /**
     * This method keeps looping until a valid input (a valid Integer value) has been parsed by the user. <br>
     * The validation happens within a try-catch block. If the user enters something which isn't of Integer type, the
     * catch clause NumberFormatException gets triggered, and prints out an informative message, declaring bad input value.
     * @return An Integer value based on input from user
     *
     * @implNote 1 .. * = Valid inputs <br>
     * a - z / !"#¤%&/ = Invalid inputs
     */
    public int readInteger() {
        while (true) {
            try {
                return Integer.parseInt(input.next());
            } catch (NumberFormatException e) {
                System.out.println("An invalid input was given, please enter a number");
            } // End of try-catch block
        } // End of while loop
    } // End of method


    /**
     *
     * @return A String with only letter values
     */
    public String readLine() {
        while (true) {
            String readLine = input.nextLine();
            if (!readLine.matches(".*\\d.*")) {
                return readLine;
            } // End of if statement
            System.out.println("Please enter a valid word/statement, without any numbers inside");
        } // End of while loop
    } // End of readLine method

    public String readNext() {
        while (true) {
            String readLine = input.next();
            if (!readLine.matches(".*\\d.*")) {
                return readLine;
            } // End of if statement
            System.out.println("Please enter a valid word/name, without any numbers inside");
        } // End of while loop
    } // End of method

    public boolean readBoolean() {
        while (true) {
            try {
                return Boolean.parseBoolean(input.next());
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter either true or false");
            } // End of try-catch block
        } // End of while loop
    } // End of method

    public double readDouble() {
        // TODO - implement UI.readDouble
        throw new UnsupportedOperationException();
    } // End of method

    public LocalDate readDate() {
        // TODO - implement UI.readDate
        throw new UnsupportedOperationException();
    }


    // Invalid Print statements --------------------------------------------------
    public String invalidChoiceInput() {
        return "Invalid input was given";
    }

}