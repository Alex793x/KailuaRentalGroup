package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

import java.util.Arrays;
import java.util.Scanner;

public class CustomerMenu extends Menu implements DBStandardQueries {
    /**
     * @param menuHeader
     * @param menuItems
     */
    private DB_Dependencies db_dependencies = DB_Dependencies.getInstance();
    private String[] printColumnNames = {"Customer ID", "name", "address", "ZIP", "City", "Phone no.",
            "Email", "Driver license number", "Driver license since"};

    public CustomerMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }

    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {
        String query = "SELECT * FROM customer_info;";
        requestHandler.printQueryResult(query, printColumnNames, db_dependencies.CUSTOMER_COLUMNS);
    }

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {
        String query = "SELECT * FROM customer_info ORDER BY customer_name;";
        requestHandler.printQueryResult(query, printColumnNames, db_dependencies.CUSTOMER_COLUMNS);
    }


    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler, DB_QueryRequestHandler requestHandler, UI ui) {
        String selectSection = String.join(", ",
                Arrays.stream(DB_Dependencies.getInstance().CUSTOMER_COLUMNS).skip(1).toArray(String[]::new));

        String sql = "INSERT INTO " + DB_Dependencies.getInstance().TABLE_NAMES[0] + " " +
                "(" + selectSection + ") \n" +
                "VALUES (" +
                ui.insertInto(
                        DB_Dependencies.getInstance().CUSTOMER_COLUMNS,
                        requestHandler,
                        DB_Dependencies.getInstance().TABLE_NAMES[0]) + ")";

        editingHandler.insertQuery(sql);
    }

    @Override
    public void updateTable(DB_QueryEditingHandler editingHandler,
                            DB_QueryRequestHandler requestHandler, UI ui) { // DB REGUEST HANDLER
        String query = "UPDATE customer_info " + "SET " +
                getTableNamesWithValues(ui) +
                " WHERE " + db_dependencies.CUSTOMER_COLUMNS[0] + " = " +
                getCustomerID(requestHandler, ui) + ";";
        System.out.println(query);
        editingHandler.insertQuery(query);
    }

    @Override
    public void deleteFromTable(DB_QueryEditingHandler editingHandler,
                                DB_QueryRequestHandler requestHandler, UI ui) {
        String query = "DELETE FROM customer_info " +
                "WHERE " + db_dependencies.CUSTOMER_COLUMNS[0] + " = " +
                getCustomerID(requestHandler, ui) + ";";

        editingHandler.insertQuery(query);
    }

    @Override
    public void alterTable() {

    }

    private String getSpecifiedTableNames(int... x) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < x.length; i++) {
            if (i == x.length - 1) {
                stringBuilder.append(db_dependencies.CUSTOMER_COLUMNS[x[i]]);
            } else {
                stringBuilder.append(db_dependencies.CUSTOMER_COLUMNS[x[i]] + ", ");
            }
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    private String getValuesToInsert(UI ui) {
        Scanner scan = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < printColumnNames.length; i++) { // magicnumber 8 is amount of values for MySQL insertion xD
            System.out.print("Please enter " + printColumnNames[i]);
            String input = scan.nextLine();
            if (i != 8) {
                if (printColumnNames[i].contains("number") || printColumnNames[i].contains("ZIP")) {
                    stringBuilder.append(input + ", ");
                } else {
                    stringBuilder.append("'" + input + "'" + ", ");
                }
            } else {
                stringBuilder.append("'" + input + "'");
            }
        }
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    private String getTableNamesWithValues(UI ui) {
        StringBuilder stringBuilder = new StringBuilder();
        ui.readNextLine(); //scannerbug

        System.out.println("If the value shouldn't be changed just type \"stay\".");
        for (int i = 1; i < 8; i++) {
            System.out.print("Please enter new value for " + printColumnNames[i] + ": ");
            String input = printColumnNames[i].contains("since") ? ui.readDate().toString() : ui.readNextLine();
            System.out.println();
            if (input.equalsIgnoreCase("stay")) {
                continue;
            }
            if (printColumnNames[i].contains("ZIP") || printColumnNames[i].contains("since")) {
                stringBuilder.append(db_dependencies.CUSTOMER_COLUMNS[i] + " = " + input + ", ");
            } else {
                stringBuilder.append(db_dependencies.CUSTOMER_COLUMNS[i] + " = '" + input + "', ");
            }
        }

        return stringBuilder.substring(0, stringBuilder.length() - 2);
    }

    private int getCustomerID(DB_QueryRequestHandler requestHandler, UI ui) {
        showTable(requestHandler);
        System.out.print("Please enter the customers ID: ");
        return ui.readInteger();
    }
}
