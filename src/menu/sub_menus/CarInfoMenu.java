package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

import java.util.Arrays;

public class CarInfoMenu extends Menu implements DBStandardQueries {
    /**
     * @param menuHeader
     * @param menuItems
     */
    private final DB_Dependencies db_dependencies = DB_Dependencies.getInstance();

    /**
     * The CarInfoMenu constructor
     *
     /**
     * @param menuHeader
     * @param menuItems
     */
    public CarInfoMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }


    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT * FROM " + db_dependencies.TABLE_NAMES[1];
        requestHandler.printQueryResult(
                sql,
                db_dependencies.CAR_PROPERTIES_PRINT_FORMAT,
                db_dependencies.CAR_PROPERTIES_COLUMNS);

    }

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT * " +
                "FROM " + db_dependencies.TABLE_NAMES[1] + " " +
                "ORDER BY " + db_dependencies.CAR_PROPERTIES_COLUMNS[6] + " ASC";

        requestHandler.printQueryResult(
                sql,
                db_dependencies.CAR_PROPERTIES_PRINT_FORMAT,
                db_dependencies.CAR_PROPERTIES_COLUMNS);
    }

    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler, DB_QueryRequestHandler requestHandler, UI ui) {

        String selectCarPropertiesSelection = String.join(", ",
                Arrays.stream(db_dependencies.CAR_PROPERTIES_COLUMNS).skip(1).toArray(String[]::new));


        String insertNewCarPropertiesSQL = "INSERT INTO " + db_dependencies.TABLE_NAMES[1] + " " +
                "(" + selectCarPropertiesSelection + ") \n" +
                "VALUES (" +
                ui.insertInto(
                        db_dependencies.CAR_PROPERTIES_COLUMNS,
                        requestHandler,
                        db_dependencies.TABLE_NAMES[1], true, false) + ")";

        editingHandler.insertQuery(insertNewCarPropertiesSQL);
    }

    @Override
    public void updateTable(DB_QueryEditingHandler editingHandler,  DB_QueryRequestHandler requestHandler, UI ui) {
        String query = "UPDATE car_registry " + "SET " +
                ui.insertInto(db_dependencies.CAR_PROPERTIES_COLUMNS,
                        requestHandler, db_dependencies.TABLE_NAMES[1], false, false) +

                " WHERE " + ui.chooseWhereOptions(
                db_dependencies.TABLE_NAMES[1], db_dependencies.CAR_PROPERTIES_COLUMNS,
                requestHandler) + ";";

        System.out.println(query);
        editingHandler.insertQuery(query);
    }

    @Override
    public void deleteFromTable(DB_QueryEditingHandler editingHandler,  DB_QueryRequestHandler requestHandler, UI ui) {
        showTable(requestHandler);
        String query = "DELETE FROM car_registry " +
                "WHERE " + ui.chooseWhereOptions(
                db_dependencies.TABLE_NAMES[1], db_dependencies.CAR_PROPERTIES_COLUMNS,
                requestHandler) + ";";

        editingHandler.insertQuery(query);
    }
}
