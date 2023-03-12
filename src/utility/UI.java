package utility;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryRequestHandler;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


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
    private final DB_Dependencies db_dependencies = DB_Dependencies.getInstance();


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
            if (!readLine.matches(".*\\d.*")) {
                return readLine;
            } // End of if statement
            System.out.println("Please enter a valid word/name, with no numbers please");
        } // End of while loop
    } // End of readLine method


    public String readNext() {
        while (true) {
            String readLine = input.next();
            if (!readLine.matches(".*\\d.*")) {
                return readLine;
            } // End of if statement
            System.out.println("Please enter a valid word/name, with no numbers please");
        } // End of while loop
    } // End of method

    public String readNextLine() {
        return input.nextLine();
    }

    public String readRegistration() {
        return input.nextLine();
    }

    public boolean readBoolean() {
        while (true) {
            System.out.println("""
                    Please choose -
                    1. true
                    2. false""");

            switch (readInteger()) {
                case 1 -> {return true;}
                case 2 -> {return false;}
                default -> System.out.println("Please enter either true or false");
            }
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
        while (true) {
            System.out.println("Please enter year");
            int year = readInteger();
            System.out.println("Please enter month");
            int month = readInteger();
            System.out.println("Please enter day");
            int day = readInteger();

            try {
                return LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                System.out.println("Please enter a valid date");
            } // End of try-catch block
        } // End of while loop
    } // End of method

    private boolean readStay(String s) {
        System.out.print(s);
        String response = input.nextLine().trim().toLowerCase();
        return response.equals("stay");
    }


    // Insert into Generic Method -----------------------------------------------

    /**
     * This method builds a sql insert statement by iterating through available columns, whenever a column
     * has been targeted, the method first creates a request to figure out the datatype, when the datatype has been figured,
     * the right {@link #UI} method which is error proofed against wrong input.
     *
     * @param columnValues   The column values which are available within a specific table
     * @param requestHandler The Database Request Handler class
     * @param tableName      The specific name of the table from DB we wish to work with
     * @return A string made up of all the values needed for a valid insert statement within specific table.
     */
    public String insertInto(String[] columnValues, DB_QueryRequestHandler requestHandler, String tableName, boolean isInsert) {
        StringBuilder insertValues = new StringBuilder();
        input.nextLine();

        Arrays.stream(columnValues).skip(1).forEach(columnElement -> {
            boolean isStay = !isInsert && readStay("If the value shouldn't be changed for " + columnElement + " just type \"stay\", else type \"edit\": ");

            if (!isStay) {
                if (!columnElement.equals(db_dependencies.CAR_REGISTRY_COLUMNS[7]) && isInsert) {
                    System.out.print("Please enter value for " + columnElement + ": ");
                }
                String dataType = requestHandler.getColumnDataType(tableName, columnElement);
                String columnValue = isInsert ? getInsertValue(columnElement, dataType) : getUpdateValue(columnElement, dataType);

                if (columnValue != null) {
                    insertValues.append(columnValue).append(",");
                } // End of inner if statement
            } // End of outer if statement
        }); // End of Arrays.stream.forEach loop
        System.out.println();

        return insertValues.substring(0, Math.max(0, insertValues.length() - 1)); // remove trailing comma
    } // End of method

    /**
     * <p> The {@link #getInsertValue(String columnElement, String dataType)} validates the datatype of the specified column which
     * is intended to be filled out for an INSERT TO statement with MySQL.
     * When the datatype has been validated and known, the method requests the correct input method, to avoid bad inputs
     * based on the datatype which the database requites for the specific column</p>
     *
     * @param columnElement The column element name
     * @param dataType  The type of data which is registered within the Database
     * @return The Insert SQL statement, where every column element is defined for insertion
     *
     *
     */
    private String getInsertValue(String columnElement, String dataType) {
        if (columnElement.equals(db_dependencies.CAR_REGISTRY_COLUMNS[3]) ||
                columnElement.equals(db_dependencies.CUSTOMER_COLUMNS[2]) ||
                columnElement.equals(db_dependencies.CUSTOMER_COLUMNS[5])) {
            return "'" + readRegistration() + "'";
        } else {
            switch (dataType) {
                case "int" -> {return readInteger() + input.nextLine();}        // Scanner bug
                case "varchar" -> {return "'" + readLine() + "'";}
                case "date" -> {return "'" + readDate() + "'";}
                case "tinyint" -> {return columnElement.equals(db_dependencies.CAR_REGISTRY_COLUMNS[7]) ? "false" : String.valueOf(readBoolean());}
                case "double" -> {return String.valueOf(readDouble());}
                default -> {
                    System.out.println("Error: Unsupported data type " + dataType);
                    return null;
                } // End of last switch case
            } // End of switch statement
        } // End of if-else statement
    } // End of method

    /**
     * <p> The {@link #getUpdateValue(String columnElement, String dataType)} is very similar to
     * {@link #getInsertValue(String columnElement, String dataType)}, but it do have a slightly different approach.
     * Before it validates the datatype of the specified column, the method request the user to choose, if the column element
     * should be changed or not, if user enters "stay", the method continues and skips the element, which
     * is intended to be filled out for an UPDATE SET statement with MySQL.
     * When the datatype has been validated and known, the method requests the correct input method, to avoid bad inputs
     * based on the datatype which the database requites for the specific column</p>
     *
     * @param columnElement The column element name
     * @param dataType  The type of data which is registered within the Database
     * @return The UPDATE SET SQL statement, where every column element is defined for insertion
     */

    private String getUpdateValue(String columnElement, String dataType) {
        if (columnElement.equals(db_dependencies.CAR_REGISTRY_COLUMNS[3]) ||
                columnElement.equals(db_dependencies.CUSTOMER_COLUMNS[2]) ||
                columnElement.equals(db_dependencies.CUSTOMER_COLUMNS[5])) {

            return columnElement + " = '" + readRegistration();

        } else {
            switch (dataType) {
                case "int" -> {return columnElement + " = " + readInteger();}
                case "varchar" -> {return columnElement + " = '" + readLine() + "'";}
                case "date" -> {return columnElement + " = '" + readDate() + "'";}
                case "tinyint" -> {return columnElement + " = " + readBoolean();}
                case "double" -> {return columnElement + " = " + readDouble();}
                default -> {
                    System.out.println("Error: Unsupported data type " + dataType);
                    return null;
                } // End of last switch case
            } // End of switch statement
        } // End of if-else statement
    } // End of method

    /**
     * <p> The {@link #chooseWhereOptions(String columnTable, String[] columnValues, DB_QueryRequestHandler)}
     * method is a generic solution to help create more specified user possible WHERE clauses.
     * In essence a user will be able to specify more than one pair of parameters to create a sophisticated WHERE statement</p>
     * @param columnTable   The Table name intended to query
     * @param columnValues  The column elements which resides within the specific table
     * @param requestHandler {@link DB_QueryRequestHandler}
     * @return Returns a filled out WHERE statement which can if amount of clauses > 1 define more than one column to express
     * search parameter for.
     */
    public String chooseWhereOptions(String columnTable, String[] columnValues, DB_QueryRequestHandler requestHandler) {
        System.out.println("How many clauses, do you want to search parameters for?: ");
        int amountOfClauses = readInteger();
        input.nextLine(); // Scanner bug

        StringBuilder whereClause = new StringBuilder();

        for (int i = amountOfClauses; i > 0; i--) {
            AtomicInteger count = new AtomicInteger(1);
            System.out.println("Please enter what specific search parameter you want to change for: ");
            Arrays.stream(columnValues).skip(1).forEach(value -> {
                System.out.println(count.getAndIncrement() + ": " + value);
            });

            System.out.println();
            String columnValue = columnValues[readInteger()] + input.nextLine(); // Scanner bug
            String dataType = requestHandler.getColumnDataType(columnTable, columnValue);
            whereClause.append(columnValue).append(" = ").append(getInsertValue(columnValue, dataType));

            if (i == 1) {
                return whereClause.toString();
            } else {
                whereClause.append(" AND ");
            } // End of if-else statement
        } // End of for loop
        return whereClause.toString();
    } // End of method

    public int parseValue(int valueToParse) {
        return valueToParse;
    }

    // Invalid Print statements --------------------------------------------------
    public String invalidChoiceInput() {
        return "Invalid input was given";
    }

}