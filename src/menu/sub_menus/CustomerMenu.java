package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

public class CustomerMenu extends Menu implements DBStandardQueries {
    /**
     * @param menuHeader
     * @param menuItems
     */
    private DB_Dependencies db_dependencies = DB_Dependencies.getInstance();
    private String[] printColumnNames = {"Customer ID: ", "name: ", "address: ", "ZIP: ", "City ", "Phone no.: ",
            "Email: ", "Driver license since: "};

    public CustomerMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }

    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {
        String query = "SELECT * FROM customer_info;";
        requestHandler.printQuery(query, printColumnNames, db_dependencies.CUSTOMER_COLUMNS);
    }

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {
        String query = "SELECT * FROM customer_info ORDER BY customer_name;";
        requestHandler.printQuery(query, printColumnNames, db_dependencies.CUSTOMER_COLUMNS);
    }

    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler,DB_QueryRequestHandler requestHandler, UI ui) {
        String query = "INSERT INTO customer_info " + "(" +
                getSpecifiedTableNames(1, 2, 3, 4, 5, 6, 7, 8) +
                ")" + " VALUES (" + getValuesToInsert(ui) + ");";

        editingHandler.insertQuery(query);
    }

    @Override
    public void updateTable(DB_QueryEditingHandler editingHandler,
                            DB_QueryRequestHandler requestHandler, UI ui) { // DB REGUEST HANDLER
        String query = "UPDATE customer_info " + "SET " +
                getTableNamesWithValues(ui) +
                "WHERE " + db_dependencies.CUSTOMER_COLUMNS[0] + " = " +
                getCustomerID(requestHandler, ui) + ";";

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
                stringBuilder.append(db_dependencies.CUSTOMER_COLUMNS[i]);
            } else {
                stringBuilder.append(db_dependencies.CUSTOMER_COLUMNS[i] + ", ");
            }
        }
        return stringBuilder.toString();
    }

    private String getValuesToInsert(UI ui) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < 8; i++) { // magicnumber 8 is amount of values for MySQL insertion xD
            System.out.print("Please enter " + printColumnNames[i] + ": ");
            stringBuilder.append(ui.readLine());
            System.out.println();
        }
        return stringBuilder.toString();
    }

    private String getTableNamesWithValues(UI ui) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("If the value shouldn't be changed just type \"stay\".");
        for (int i = 1; i < 8; i++) {
            System.out.print("Please enter new value for " + printColumnNames[i] + ": ");
            String input = ui.readLine();
            System.out.println();
            if (input.equalsIgnoreCase("stay")) {
                continue;
            }
            if (i == 7) {
                stringBuilder.append(db_dependencies.CUSTOMER_COLUMNS[i] + " = " + input);
            } else {
                stringBuilder.append(db_dependencies.CUSTOMER_COLUMNS[i] + " = " + input + ", ");
            }
        }

        return stringBuilder.toString();
    }

    private int getCustomerID(DB_QueryRequestHandler requestHandler, UI ui) {
        int customerID = 0;
        showTable(requestHandler);

        System.out.print("Please enter the customers ID: ");
        customerID = ui.readInteger();

        return customerID;
    }
}
