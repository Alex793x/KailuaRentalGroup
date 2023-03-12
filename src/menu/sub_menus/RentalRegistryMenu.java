package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

import java.util.Arrays;

public class RentalRegistryMenu extends Menu implements DBStandardQueries {

    private final DB_Dependencies db_dependencies = DB_Dependencies.getInstance();

    /**
     * The CarInfoMenu constructor
     *
    /**
     * @param menuHeader
     * @param menuItems
     */
    public RentalRegistryMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }


    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT * FROM " + db_dependencies.TABLE_NAMES[2];
        requestHandler.printQueryResult(
                sql,
                db_dependencies.RENTAL_REGISTRY_COLUMNS_PRINT_FORMAT,
                db_dependencies.RENTAL_REGISTRY_COLUMNS);

    }

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {
        String sql = "SELECT * " +
                "FROM " + db_dependencies.TABLE_NAMES[2] + " " +
                "ORDER BY " + db_dependencies.RENTAL_REGISTRY_COLUMNS[1] + " ASC";

        requestHandler.printQueryResult(
                sql,
                db_dependencies.RENTAL_REGISTRY_COLUMNS_PRINT_FORMAT,
                db_dependencies.RENTAL_REGISTRY_COLUMNS);
    }

    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler, DB_QueryRequestHandler requestHandler, UI ui) {

        String selectLeasingAgreementSelection = String.join(", ",
                Arrays.stream(db_dependencies.RENTAL_REGISTRY_COLUMNS).skip(1).toArray(String[]::new));


        String insertNewLeasingAgreementSQL = "INSERT INTO " + db_dependencies.TABLE_NAMES[2] + " " +
                "(" + selectLeasingAgreementSelection + ") \n" +
                "VALUES (" +
                ui.insertInto(
                        db_dependencies.RENTAL_REGISTRY_COLUMNS,
                        requestHandler,
                        db_dependencies.TABLE_NAMES[2], true) + ")";

        editingHandler.insertQuery(insertNewLeasingAgreementSQL);
    }

    @Override
    public void updateTable(DB_QueryEditingHandler editingHandler,  DB_QueryRequestHandler requestHandler, UI ui) {
        String query = "UPDATE car_registry " + "SET " +
                ui.insertInto(db_dependencies.RENTAL_REGISTRY_COLUMNS,
                        requestHandler, db_dependencies.TABLE_NAMES[2], false) +

                " WHERE " + ui.chooseWhereOptions(
                db_dependencies.TABLE_NAMES[2], db_dependencies.RENTAL_REGISTRY_COLUMNS,
                requestHandler) + ";";

        System.out.println(query);
        editingHandler.insertQuery(query);
    }

    @Override
    public void deleteFromTable(DB_QueryEditingHandler editingHandler,  DB_QueryRequestHandler requestHandler, UI ui) {
        /*showTable(requestHandler);
        String query = "DELETE FROM car_registry " +
                "WHERE " + ui.chooseWhereOptions(
                db_dependencies.TABLE_NAMES[2], db_dependencies.RENTAL_REGISTRY_COLUMNS,
                requestHandler) + ";";



        System.out.println("Now we need to do the same for Car Registry associated with the deleted Leasing Agreement");
        System.out.print("Please enter car_registry_id you just deleted: ");
        editingHandler.insertQuery(query);*/
    }
}
