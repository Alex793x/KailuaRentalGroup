package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

import java.util.Arrays;

public class CarRegistryMenu extends CarPropertiesMenu implements DBStandardQueries {
    private final DB_Dependencies db_dependencies = DB_Dependencies.getInstance();

    /**
     * The CarInfoMenu constructor
     *
     * @param menuHeader Title of the menu
     * @param menuItems  The different menu options
     */
    public CarRegistryMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }


    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT * FROM " + db_dependencies.TABLE_NAMES[3];
        requestHandler.printQueryResult(
                sql,
                db_dependencies.CAR_REGISTRY_COLUMN_PRINT_FORMAT,
                db_dependencies.CAR_REGISTRY_COLUMNS);
    } // End of method

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT * " +
                "FROM " + db_dependencies.TABLE_NAMES[3] + " " +
                "ORDER BY " + db_dependencies.CAR_REGISTRY_COLUMNS[1] + " ASC";

        requestHandler.printQueryResult(
                sql,
                db_dependencies.CAR_REGISTRY_COLUMN_PRINT_FORMAT,
                db_dependencies.CAR_REGISTRY_COLUMNS);
    } // End of method

    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler, DB_QueryRequestHandler requestHandler, UI ui) {

        String selectCarRegistrySelection = String.join(", ",
                Arrays.stream(db_dependencies.CAR_REGISTRY_COLUMNS).skip(1).toArray(String[]::new));


        String insertNewCarSQL = "INSERT INTO " + db_dependencies.TABLE_NAMES[3] + " " +
                "(" + selectCarRegistrySelection + ") \n" +
                "VALUES (" +
                ui.insertInto(
                        db_dependencies.CAR_REGISTRY_COLUMNS,
                        requestHandler,
                        db_dependencies.TABLE_NAMES[3], true, false) + ")";

        super.insertToTable(editingHandler,requestHandler,ui);
        editingHandler.insertQuery(insertNewCarSQL);
    } // End of method

    @Override
    public void updateTable(DB_QueryEditingHandler editingHandler,  DB_QueryRequestHandler requestHandler, UI ui) {
        String query = "UPDATE car_registry " + "SET " +
                ui.insertInto(db_dependencies.CAR_REGISTRY_COLUMNS,
                        requestHandler, db_dependencies.TABLE_NAMES[3], false, false) +

                " WHERE " + ui.chooseWhereOptions(
                        db_dependencies.TABLE_NAMES[3], db_dependencies.CAR_REGISTRY_COLUMNS,
                requestHandler) + ";";

        System.out.println(query);
        editingHandler.insertQuery(query);
    } // End of method

    @Override
    public void deleteFromTable(DB_QueryEditingHandler editingHandler,  DB_QueryRequestHandler requestHandler, UI ui) {

        showTable(requestHandler);
        System.out.println("Please enter ID for car to delete");
        int carID = ui.readInteger();
        String checkQuery = "SELECT * FROM " + db_dependencies.TABLE_NAMES[2];
        if (requestHandler.checkIfExists(checkQuery, carID, 4)) {
            System.out.println("The car is no longer possible to delete, it has been rented out. If " +
                    "no longer possible to rent the car, please edit the \"avalability\" property for the car\n");
        } else {

            String query = "DELETE FROM car_registry " +
                    "WHERE " + ui.chooseWhereOptions(
                    db_dependencies.TABLE_NAMES[3], db_dependencies.CAR_REGISTRY_COLUMNS,
                    requestHandler) + ";";

            System.out.println("Now we need to do the same for Car Properties associated with the deleted car");
            System.out.print("Please enter car_registry_id you just deleted: ");
            editingHandler.insertQuery(query);
            super.deleteFromTable(editingHandler, requestHandler, ui);
        } // End of if-else statement
    } // End of method

}