package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryRequestHandler;
import menu.Menu;

public class AnalysisMenu extends Menu {
    public AnalysisMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }


    // Methods ---------------------------------------------------------------------
    public void showTable(DB_QueryRequestHandler requestHandler, DB_Dependencies db_dependencies) {
        String sql = "SELECT " + DB_Dependencies.getInstance().CUSTOMER_COLUMNS[1] + ", " +
                db_dependencies.RENTAL_REGISTRY_COLUMNS[4] + ", " + "SUM(DATEDIFF(" +
                db_dependencies.RENTAL_REGISTRY_COLUMNS[2] + "," +
                db_dependencies.RENTAL_REGISTRY_COLUMNS[1] + ")) AS total_days_rented FROM " +
                db_dependencies.TABLE_NAMES[2] + " JOIN " +
                db_dependencies.TABLE_NAMES[0] + " ON " +
                db_dependencies.RENTAL_REGISTRY_COLUMNS[4] + " = " +
                db_dependencies.CUSTOMER_COLUMNS[0] + " GROUP BY " +
                db_dependencies.RENTAL_REGISTRY_COLUMNS[4] + " ORDER BY " +
                "total_days_rented DESC;";
        requestHandler.printQueryResult(
                sql,
                db_dependencies.bestCustomerPrint,
                db_dependencies.BestCustomerDB);
    } // End of method

}
