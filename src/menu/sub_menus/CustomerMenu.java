package menu.sub_menus;

import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;

public class CustomerMenu extends Menu implements DBStandardQueries {
    /**
     * @param menuHeader
     * @param menuItems
     */
    public CustomerMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }

    @Override
    public void showTable() {

    }

    @Override
    public void showTableOrdered() {

    }

    @Override
    public void insertToTable() {

    }

    @Override
    public void updateTable() {

    }

    @Override
    public void deleteFromTable() {

    }

    @Override
    public void alterTable() {

    }
}
