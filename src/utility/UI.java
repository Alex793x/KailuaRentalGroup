package utility;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryRequestHandler;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;


/**
 * <p>The {@code UI} class will be handling all inputs from user, the purpose is to make sure
 * that all processed inputs from user, always goes through the same class, minimizing the search pair when ever
 * an error occurs from the {@code Scanner} class </p>
 * <br>
 *
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
     *
     * @return An Integer value based on input from user
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
     * @return A String with only letter values
     */
    public String readLine() {
        while (true) {
            String readLine = input.nextLine();
            if (!readLine.matches(".*\\d.*") && !readLine.isEmpty()) {
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
            System.out.println("""
                    Please choose -
                    1. true
                    2. false""");

            switch (readInteger()) {
                case 1 -> {return true;}
                case 0 -> {return false;}
                default -> System.out.println("Please enter either true or false");}
        } // End of while loop
    } // End of method


    public double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(readNext());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid double value");
            } // End of try-catch block
        } // End of while loop
    } // End of method



    public LocalDate readDate() {
        while(true) {
            System.out.println("Please enter year");
            int year = readInteger();
            System.out.println("Please enter month");
            int month = readInteger();
            System.out.println("Please enter day");
            int day = readInteger();

            try {
                return LocalDate.of(year,month,day);
            } catch (DateTimeException e) {
                System.out.println("Please enter a valid date");
            } // End of try-catch block
        } // End of while loop
    } // End of method


    // Insert into Generic Method -----------------------------------------------
    public String insertInto(String[] columnValues, DB_QueryRequestHandler requestHandler, String tableName) {
        StringBuilder insertValues = new StringBuilder();

        Arrays.stream(columnValues).forEach(s -> {
            String dataType = requestHandler.getColumnDataType(tableName, s);
            System.out.println("Please enter value for " + s + ": ");
            switch (dataType) {
                case "INT" -> insertValues.append(readInteger()).append(",");
                case "VARCHAR" -> insertValues.append(readLine()).append(",");
                case "DATE" -> insertValues.append(readDate()).append(",");
                case "BOOLEAN" -> insertValues.append(readBoolean()).append(",");
                case "DOUBLE" -> insertValues.append(readDouble()).append(",");
                default -> System.out.println("Error: Unsupported data type " + dataType);
            }
        });
        return insertValues.toString();
    }


    // Invalid Print statements --------------------------------------------------
    public String invalidChoiceInput() {
        return "Invalid input was given";
    }

}