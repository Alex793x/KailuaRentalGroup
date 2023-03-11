package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

import java.util.Arrays;

public class CarPropertiesMenu extends Menu implements DBStandardQueries {

    private final DB_Dependencies db_dependencies = DB_Dependencies.getInstance();

    /**
     * @param menuHeader
     * @param menuItems
     */
    public CarPropertiesMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }


    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT * FROM " + db_dependencies.TABLE_NAMES[1];

        requestHandler.printQueryResult(sql,
                db_dependencies.CAR_PROPERTIES_PRINT_FORMAT,
                db_dependencies.CAR_PROPERTIES_COLUMNS);
    }

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT * FROM " + db_dependencies.TABLE_NAMES[1] + " " +
                "ORDER BY " + db_dependencies.CAR_PROPERTIES_COLUMNS[9] + " ASC";

        requestHandler.printQueryResult(sql,
                db_dependencies.CAR_PROPERTIES_PRINT_FORMAT,
                db_dependencies.CAR_PROPERTIES_COLUMNS);
    }

    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler, DB_QueryRequestHandler requestHandler, UI ui) {
        String selectCarPropertySelection = String.join(", ",
                Arrays.stream(db_dependencies.CAR_PROPERTIES_COLUMNS).skip(1).toArray(String[]::new));

        String insertPropertiesSQL = "INSERT INTO " + db_dependencies.TABLE_NAMES[1] + " " +
                "(" + selectCarPropertySelection + ") \n" +
                "VALUES (" +
                ui.insertInto(db_dependencies.CAR_PROPERTIES_COLUMNS,
                        requestHandler,
                        db_dependencies.TABLE_NAMES[1], true) + ")";

        editingHandler.insertQuery(insertPropertiesSQL);
    }

    @Override
    public void updateTable(DB_QueryEditingHandler editingHandler, DB_QueryRequestHandler requestHandler, UI ui) {
        String query = "UPDATE car_registry " + "SET " +
                ui.insertInto(db_dependencies.CAR_PROPERTIES_COLUMNS,
                        requestHandler, db_dependencies.TABLE_NAMES[1], false) +

                " WHERE " + ui.chooseWhereOptions(
                db_dependencies.TABLE_NAMES[1], db_dependencies.CAR_PROPERTIES_COLUMNS,
                requestHandler) + ";";

        System.out.println(query);
        editingHandler.insertQuery(query);
    }

    @Override
    public void deleteFromTable(DB_QueryEditingHandler editingHandler, DB_QueryRequestHandler requestHandler, UI ui) {
        
    }

    @Override
    public void alterTable() {

    }
}
