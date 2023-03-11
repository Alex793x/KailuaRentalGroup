package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

public class LeasingAgreementMenu extends Menu implements DBStandardQueries {

    private final DB_Dependencies db_dependencies = DB_Dependencies.getInstance();
    /**
     * @param menuHeader
     * @param menuItems
     */
    public LeasingAgreementMenu(String menuHeader, String[] menuItems) {
        super(menuHeader, menuItems);
    }


    @Override
    public void showTable(DB_QueryRequestHandler requestHandler) {
        String query = "SELECT * FROM " + DB_Dependencies.getInstance().TABLE_NAMES[2];

        requestHandler.printQueryResult(query, DB_Dependencies.getInstance().LEASING_AGREEMENT_COLUMN_PRINT_FORMAT,
                DB_Dependencies.getInstance().LEASING_AGREEMENT_COLUMNS);
    }

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {
        String query = "SELECT * FROM " + DB_Dependencies.getInstance().TABLE_NAMES[2] + " ORDER BY "
                + DB_Dependencies.getInstance().LEASING_AGREEMENT_COLUMNS[1] + " ASC";

        requestHandler.printQueryResult(query, DB_Dependencies.getInstance().LEASING_AGREEMENT_COLUMN_PRINT_FORMAT,
                DB_Dependencies.getInstance().LEASING_AGREEMENT_COLUMNS);
    }

    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler, DB_QueryRequestHandler requestHandler, UI ui) {
        System.out.println("Input Year, Month and Day of Rental Start");
        LocalDate dateStart = ui.readDate();
        System.out.println("Input Year, Month and Day of rental end");
        LocalDate dateEnd = ui.readDate();
        System.out.println("Input car registry id");
        int carRegId = ui.readInteger();
        System.out.println("Input customer id");
        int customerId = ui.readInteger();
        String query = "INSERT INTO " + DB_Dependencies.getInstance().TABLE_NAMES[2] + " " +
                "(" + Arrays.toString(Arrays.copyOfRange(DB_Dependencies.getInstance().LEASING_AGREEMENT_COLUMNS,1,5)) +
                " ) VALUES (?,?,?,?)";

        editingHandler.insertQuery(query);
        System.out.println("New rental agreement created");
    }

    @Override
    public void updateTable(DB_QueryEditingHandler editingHandler,  DB_QueryRequestHandler requestHandler, UI ui) {

    }

    @Override
    public void deleteFromTable(DB_QueryEditingHandler editingHandler,  DB_QueryRequestHandler requestHandler,
                                UI ui) {

    }

    @Override
    public void alterTable() {

    }

    public void getAvailableCars(DB_QueryRequestHandler requestHandler, UI ui) {

        // De gør præcis det samme
        String query1 = "SELECT cr.car_registry_id, cr.car_brand, cr.car_model, crg.car_rental_group_name\n" +
                "FROM car_registry cr\n" +
                "JOIN car_rental_groups crg USING (car_rental_group_id)\n" +
                "WHERE cr.availability = 1 AND cr.car_isRented = 0";


        /*
         * Dette eksempel er for at vise hvorfor generiske metoder KAN være smarte.
         * Denne version gør det samme, som din ovenfor men bruger en generisk WHERE creator.
         * Man vælger selv hvilke klausuler man vil søge efter baseret på de koloner man giver med, derefter indtaster man
         * index positionen, som printes hvorefter den rette UI metode som kaldes på baggrund af datatypen som DB kolonen er indekseret med
         * resultatet der bliver appended efter WHERE delen.
         *
         * Hvis man nu ville sammensætte begge join tables kolloner ville man bare skrive:
         *
         *          Stream
         *               .concat(Arrays.stream(db_dependencies.CAR_REGISTRY_COLUMNS),
         *                      Arrays.stream(db_dependencies.CAR_RENTAL_GROUPS_COLUMNS))
         *               .toArray(String[]::new)
         *
         * Stream metoden foroven sammensætter to arrays, og dermed kan man selv vælge hvilke parameter man vil lave sin WHERE claus efter.
         *
         */
        String query2 = "SELECT " + String.join(", ", Arrays.asList(db_dependencies.CAR_REGISTRY_COLUMNS).subList(1,5)) + "\n"
                + db_dependencies.TABLE_NAMES[3] + " cr\n" +
                "JOIN " + db_dependencies.TABLE_NAMES[4] + " crg USING (" + db_dependencies.CAR_RENTAL_GROUPS_COLUMNS[0] + ")\n"
                + "WHERE " + ui.chooseWhereOptions(db_dependencies.TABLE_NAMES[3],
                db_dependencies.CAR_REGISTRY_COLUMNS, requestHandler);


        // De gør præcis det samme
        /*String query = "SELECT " + DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS[0] + ", " + DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS[1] +
                ", " + DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS[2] + ", " +
                DB_Dependencies.getInstance().CAR_RENTAL_GROUPS_COLUMNS[1] +"\n" +
                "FROM " + Arrays.toString(DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS) + "\n" +
                "JOIN " + Arrays.toString(DB_Dependencies.getInstance().CAR_RENTAL_GROUPS_COLUMNS) + " USING (" +
                DB_Dependencies.getInstance().CAR_RENTAL_GROUPS_COLUMNS[0] + ")\n" +
                "WHERE "+ DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS[6] +"= 1 AND "+
                DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS[7] +"= 0";*/
        System.out.println(query2);

        requestHandler.printQueryResult(query2, new String[] {DB_Dependencies.getInstance().CAR_REGISTRY_COLUMN_PRINT_FORMAT[0],
                DB_Dependencies.getInstance().CAR_REGISTRY_COLUMN_PRINT_FORMAT[1],DB_Dependencies.getInstance().CAR_REGISTRY_COLUMN_PRINT_FORMAT[2],
                DB_Dependencies.getInstance().CAR_RENTAL_GROUP_PRINT_FORMAT[1]}, new String[] {DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS[0],
                DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS[1],DB_Dependencies.getInstance().CAR_REGISTRY_COLUMNS[2], DB_Dependencies.getInstance().CAR_RENTAL_GROUPS_COLUMNS[1]});
    }
}
