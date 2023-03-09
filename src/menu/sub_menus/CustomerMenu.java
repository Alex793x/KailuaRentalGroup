package menu.sub_menus;

import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;

public class CustomerMenu extends Menu implements DBStandardQueries {
    /**
     * @param menuHeader
     * @param menuItems
     */

    String[] allColumnNames = {"customer_id", "customer_name", "customer_address", "customer_zip",
            "customer_city", "customer_phone", "customer_email", "customer_driver_license_number",
            "customer_driver_since"};
    public CustomerMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }


    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {
        String query = "SELECT *";
        String[] printColumnNames = {};
        String[] columnNames = {};
        requestHandler.printQuery(query, printColumnNames, columnNames);
    }

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {

    }

    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler) {

    }

    @Override
    public void updateTable(DB_QueryEditingHandler editingHandler) {

    }

    @Override
    public void deleteFromTable(DB_QueryEditingHandler editingHandler) {

    }

    @Override
    public void alterTable() {

    }
}
