package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

import java.util.Arrays;

public class CustomerMenu extends Menu implements DBStandardQueries {
    /**
     * @param menuHeader
     * @param menuItems
     */
    private final DB_Dependencies db_dependencies = DB_Dependencies.getInstance();

    public CustomerMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }

    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {
        String query = "SELECT * FROM customer_info;";
        requestHandler.printQueryResult(query, db_dependencies.CUSTOMER_COLUMNS_PRINT_FORMAT,
                db_dependencies.CUSTOMER_COLUMNS);
    }

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {
        String query = "SELECT * FROM customer_info ORDER BY customer_name;";
        requestHandler.printQueryResult(query, db_dependencies.CUSTOMER_COLUMNS_PRINT_FORMAT,
                db_dependencies.CUSTOMER_COLUMNS);
    }


    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler,
                              DB_QueryRequestHandler requestHandler, UI ui) {

        String selectSection = String.join(", ",
                Arrays.stream(DB_Dependencies.getInstance().CUSTOMER_COLUMNS).skip(1).toArray(String[]::new));

        String sql = "INSERT INTO " + db_dependencies.TABLE_NAMES[0] + " " +
                "(" + selectSection + ") \n" +
                "VALUES (" +
                ui.insertInto(
                        db_dependencies.CUSTOMER_COLUMNS,
                        requestHandler,
                        db_dependencies.TABLE_NAMES[0],true) + ")";

        editingHandler.insertQuery(sql);
    }

    @Override
    public void updateTable(DB_QueryEditingHandler editingHandler,
                            DB_QueryRequestHandler requestHandler, UI ui) { // DB REGUEST HANDLER
        String query = "UPDATE customer_info " + "SET " +
                ui.insertInto(db_dependencies.CUSTOMER_COLUMNS,
                        requestHandler,
                        db_dependencies.TABLE_NAMES[0], false) +
                " WHERE " + ui.chooseWhereOptions(db_dependencies.TABLE_NAMES[0],
                db_dependencies.CUSTOMER_COLUMNS,requestHandler);
        System.out.println(query);
        editingHandler.insertQuery(query);
    }

    @Override
    public void deleteFromTable(DB_QueryEditingHandler editingHandler,
                                DB_QueryRequestHandler requestHandler, UI ui) {
        String query = "DELETE FROM customer_info " +
                "WHERE " + ui.chooseWhereOptions(db_dependencies.TABLE_NAMES[0],
                db_dependencies.CUSTOMER_COLUMNS, requestHandler);

        editingHandler.insertQuery(query);
    }
}
