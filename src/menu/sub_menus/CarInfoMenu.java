package menu.sub_menus;

import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;

public class CarInfoMenu extends Menu implements DBStandardQueries {
    /**
     * @param menuHeader
     * @param menuItems
     */
    public CarInfoMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }


    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {

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
