package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

import java.util.Arrays;

public class CarPropertiesMenu extends Menu implements DBStandardQueries {

    public CarPropertiesMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    } // End of method


    @Override
    public void showTable(DB_QueryRequestHandler requestHandler, DB_Dependencies db_dependencies) {
        String sql = "SELECT * FROM " + db_dependencies.TABLE_NAMES[1];

        requestHandler.printQueryResult(sql,
                db_dependencies.CAR_PROPERTIES_PRINT_FORMAT,
                db_dependencies.CAR_PROPERTIES_COLUMNS);
    } // End of method

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler, DB_Dependencies db_dependencies) {
        String sql = "SELECT * FROM " + db_dependencies.TABLE_NAMES[1] + " " +
                "ORDER BY " + db_dependencies.CAR_PROPERTIES_COLUMNS[9] + " ASC";

        requestHandler.printQueryResult(sql,
                db_dependencies.CAR_PROPERTIES_PRINT_FORMAT,
                db_dependencies.CAR_PROPERTIES_COLUMNS);
    } // End of method

    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler,
                              DB_QueryRequestHandler requestHandler, UI ui, DB_Dependencies db_dependencies) {
        String selectCarPropertySelection = String.join(", ",
                Arrays.stream(db_dependencies.CAR_PROPERTIES_COLUMNS).skip(1).toArray(String[]::new));

        String insertPropertiesSQL = "INSERT INTO " + db_dependencies.TABLE_NAMES[1] + " " +
                "(" + selectCarPropertySelection + ") \n" +
                "VALUES (" +
                ui.insertInto(db_dependencies.CAR_PROPERTIES_COLUMNS,
                        requestHandler,
                        db_dependencies.TABLE_NAMES[1], true, false) + ")";

        editingHandler.insertQuery(insertPropertiesSQL);
    } // End of method

    @Override
    public void updateTable(DB_QueryEditingHandler editingHandler,
                            DB_QueryRequestHandler requestHandler, UI ui, DB_Dependencies db_dependencies) {
        String query = "UPDATE car_properties " + "SET " +
                ui.insertInto(db_dependencies.CAR_PROPERTIES_COLUMNS,
                        requestHandler, db_dependencies.TABLE_NAMES[1], false, false) +

                " WHERE " + ui.chooseWhereOptions(
                db_dependencies.TABLE_NAMES[1], db_dependencies.CAR_PROPERTIES_COLUMNS,
                requestHandler) + ";";

        System.out.println(query);
        editingHandler.insertQuery(query);
    } // End of method

    @Override
    public void deleteFromTable(DB_QueryEditingHandler editingHandler,
                                DB_QueryRequestHandler requestHandler, UI ui, DB_Dependencies db_dependencies) {
        System.out.println("Please enter Car registry ID to delete: ");
        String query = "DELETE FROM car_properties " +
                "WHERE " + db_dependencies.CAR_PROPERTIES_COLUMNS[0] + " = " + ui.readInteger() + ui.readLine();
        editingHandler.insertQuery(query);
    } // End of method

    public void searchAndShowTable(DB_QueryRequestHandler requestHandler, UI ui, DB_Dependencies db_dependencies) {
        String query = "SELECT * FROM " + db_dependencies.TABLE_NAMES[1] + " " +
                "WHERE " + ui.chooseWhereOptions(db_dependencies.TABLE_NAMES[1],
                db_dependencies.CAR_PROPERTIES_COLUMNS, requestHandler) + " " +
                "ORDER BY " + db_dependencies.CAR_PROPERTIES_COLUMNS[9];

        requestHandler.printQueryResult(query, db_dependencies.CAR_PROPERTIES_PRINT_FORMAT,
                db_dependencies.CAR_PROPERTIES_COLUMNS);
    } // End of method

    private String getGroupType(UI ui, DB_QueryRequestHandler requestHandler, DB_Dependencies db_dependencies){
        String sql = ui.insertInto(db_dependencies.CAR_PROPERTIES_COLUMNS,
                requestHandler,
                db_dependencies.TABLE_NAMES[1], true, false);

        String[] sqlCheck = sql.split(",");
        if (sqlCheck[2].equalsIgnoreCase("Automatic")){
            return sql + ", 1";
        } else if(Integer.parseInt(sqlCheck[6])>200 && Integer.parseInt(sqlCheck[5])<=5) {
            return sql + ", 3";
        } else return sql + ", 2";
    } // End of method


}