package menu.sub_menus;

import dbm.DB_Dependencies;
import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import menu.Menu;
import utility.UI;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;

public class LeasingAgreementMenu extends Menu implements DBStandardQueries {
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
        requestHandler.printQuery(query, DB_Dependencies.getInstance().LEASING_AGREEMENT_COLUMN_PRINT_FORMAT,
                DB_Dependencies.getInstance().LEASING_AGREEMENT_COLUMNS);
    }

    @Override
    public void showTableOrdered(DB_QueryRequestHandler requestHandler) {
        String query = "SELECT * FROM " + DB_Dependencies.getInstance().TABLE_NAMES[2] + " ORDER BY "
                + DB_Dependencies.getInstance().LEASING_AGREEMENT_COLUMNS[1] + " ASC";
        requestHandler.printQuery(query, DB_Dependencies.getInstance().LEASING_AGREEMENT_COLUMN_PRINT_FORMAT,
                DB_Dependencies.getInstance().LEASING_AGREEMENT_COLUMNS);
    }

    @Override
    public void insertToTable(DB_QueryEditingHandler editingHandler, UI ui) {


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
    public void updateTable(DB_QueryEditingHandler editingHandler, UI ui) {

    }

    @Override
    public void deleteFromTable(DB_QueryEditingHandler editingHandler, UI ui) {

    }

    @Override
    public void alterTable() {

    }
}
