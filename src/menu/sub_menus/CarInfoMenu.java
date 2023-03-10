package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

import java.util.Arrays;

public class CarInfoMenu extends Menu implements DBStandardQueries {

    /** The CarInfoMenu constructor
     * @param menuHeader Title of the menu
     * @param menuItems The different menu options
     */
    public CarInfoMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }



    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT * FROM " + DB_Dependencies.getInstance().TABLE_NAMES[3];
        requestHandler.printQuery(
                sql,
                DB_Dependencies.getInstance().carRegistryColumnPrintFormat,
                DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS);
    }

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT * " +
                "FROM " + DB_Dependencies.getInstance().TABLE_NAMES[3] + " " +
                "ORDERED BY " + DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS[1] + " ASC";
        requestHandler.printQuery(
                sql,
                DB_Dependencies.getInstance().carRegistryColumnPrintFormat,
                DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS);
    }

    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler, UI ui) {
        String sql = "INSERT INTO " + DB_Dependencies.getInstance().TABLE_NAMES[3] + " " +
                "(" + Arrays.toString(DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS) + ") \n" +
                "VALUES (";
    }

    @Override
    public void updateTable(DB_QueryEditingHandler editingHandler, UI ui) {

    }

    @Override
    public void deleteFromTable(DB_QueryEditingHandler editingHandler, UI ui) {

    }

    @Override
    public void alterTable() {

    }
}