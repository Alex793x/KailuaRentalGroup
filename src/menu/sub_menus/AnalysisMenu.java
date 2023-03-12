package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

import java.sql.SQLException;

public class AnalysisMenu extends Menu {
    /**
     * @param menuHeader
     * @param menuItems
     */
    public AnalysisMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }


    // Methods ---------------------------------------------------------------------
    public void showTable(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT " + DB_Dependencies.getInstance().CUSTOMER_COLUMNS[1] + ", " +
                DB_Dependencies.getInstance().rentalRegistryCOLUMNS[4] + ", " + "SUM(DATEDIFF(" +
                DB_Dependencies.getInstance().rentalRegistryCOLUMNS[2] + "," +
                DB_Dependencies.getInstance().rentalRegistryCOLUMNS[1] + ")) AS total_days_rented FROM " +
                DB_Dependencies.getInstance().TABLE_NAMES[2] + " JOIN " +
                DB_Dependencies.getInstance().TABLE_NAMES[0] + " ON " +
                DB_Dependencies.getInstance().rentalRegistryCOLUMNS[4] + " = " +
                DB_Dependencies.getInstance().CUSTOMER_COLUMNS[0] + " GROUP BY " +
                DB_Dependencies.getInstance().rentalRegistryCOLUMNS[4] + " ORDER BY " +
                "total_days_rented DESC;";
        requestHandler.printQueryResult(
                sql,
                DB_Dependencies.getInstance().bestCustomerPrint,
                DB_Dependencies.getInstance().BestCustomerDB);
    }

}
