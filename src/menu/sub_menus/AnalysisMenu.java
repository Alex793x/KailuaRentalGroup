package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

public class AnalysisMenu extends Menu implements DBStandardQueries {
    /**
     * @param menuHeader
     * @param menuItems
     */
    public AnalysisMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }

    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {

    }

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT " + DB_Dependencies.getInstance().CUSTOMER_COLUMNS[1] + ", " +
                DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS[4] + ", " + "SUM(DATEDIFF(" +
                DB_Dependencies.getInstance().rentalRegistryCOLUMNS[2] + "," +
                DB_Dependencies.getInstance().rentalRegistryCOLUMNS[1] + ")) AS total_days_rented FROM " +
                DB_Dependencies.getInstance().TABLE_NAMES[2] + " JOIN " +
                DB_Dependencies.getInstance().TABLE_NAMES[0] + " ON " +
                DB_Dependencies.getInstance().rentalRegistryCOLUMNS[4] + " = " +
                DB_Dependencies.getInstance().CUSTOMER_COLUMNS[0] + " GROUP BY " +
                DB_Dependencies.getInstance().rentalRegistryCOLUMNS[4] + " ORDER BY " +
                "total_days_rented DESC;";
                requestHandler.printQuery(sql,);


    }

    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler, UI ui) {

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
