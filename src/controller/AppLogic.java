package controller;

import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import utility.*;
import menu.handler.*;

import java.sql.SQLException;

/**
 * The AppLogic class is defining and running all the logic within the app.
 * Think about it as the controller of the whole application, requesting and handling queries
 * from each class.
 * <p>In essence different menus will be calling queries on the database for the Kailua Rental Group.</p>
 * @implNote {@link #runMainMenu()} Will be showing the different sections in categorized fashion. <br>
 * {@link #runCustomerMenu()} Will be handling all customer queries (adding new customer, delete, edit etc.) <br>
 * {@link #runLeasingAgreementMenu()} Will be handling all leasing agreements while query details on cars and specifications <br>
 * {@link #runCarRegistryMenu()} Will be handling all queries when user wants to create unique search criteria on car info <br>
 * {@link #runCarInfoMenu()} Will be handling payments, setups, while also other important employee queries <br>
 * {@link #runAnalysisMenu()} Will be handling detailed analysis queries if employees want to get some detailed information about customer behaviour <br>
 */
public class AppLogic {
    // Fields ----------------------------------------------------------------------------
    private final UI ui;
    private final MenuHandler menuHandler;
    private final DB_QueryRequestHandler queryRequestHandler;
    private final DB_QueryEditingHandler editingHandler;



    // Constructor -----------------------------------------------------------------------

    /**
     * The AppLogic constructor.
     */
    public AppLogic() {
        ui = new UI();
        menuHandler = new MenuHandler();
        queryRequestHandler = new DB_QueryRequestHandler();
        editingHandler = new DB_QueryEditingHandler();
    }


    // Methods ---------------------------------------------------------------------------

    /**
     *
     */
    public void runMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            menuHandler.mainMenu.printMenu();

            switch (ui.readInteger()) {
                case 1 -> runCustomerMenu();
                case 2 -> runCarRegistryMenu();
                case 3 -> runCarPropertyMenu();
                case 4 -> runLeasingAgreementMenu();
                case 5 -> runCarInfoMenu();
                case 6 -> runAnalysisMenu();
                case 0 -> isRunning = false;
                default -> System.out.println(ui.invalidChoiceInput());
            } // End of switch case
        } // End of while loop
        System.exit(1);
    } // End of method

    private void runCustomerMenu() {
        boolean isRunning = true;
        while (isRunning) {
            menuHandler.customerMenu.printMenu();
            switch (ui.readInteger()) {
                case 1 -> {menuHandler.customerMenu.showTable(queryRequestHandler);}
                case 2 -> {menuHandler.customerMenu.showTableOrdered(queryRequestHandler);}
                case 3 -> {menuHandler.customerMenu.insertToTable(editingHandler, queryRequestHandler,ui);}
                case 4 -> {menuHandler.customerMenu.updateTable(editingHandler, queryRequestHandler,ui);}
                case 5 -> {menuHandler.customerMenu.deleteFromTable(editingHandler, queryRequestHandler,ui);}
                case 0 -> isRunning = false;
                default -> System.out.println(ui.invalidChoiceInput());
            } // End of switch case
        } // End of while loop
    } // End of method

    private void runLeasingAgreementMenu() {
        boolean isRunning = true;
        while (isRunning){
            menuHandler.leasingAgreementMenu.printMenu();
            switch (ui.readInteger()) {
                case 1 -> {menuHandler.leasingAgreementMenu.showTable(queryRequestHandler);}
                case 2 -> {menuHandler.leasingAgreementMenu.showTableOrdered(queryRequestHandler);}
                case 3 -> {menuHandler.leasingAgreementMenu.insertToTable(editingHandler,queryRequestHandler,ui);}
                case 4 -> {menuHandler.leasingAgreementMenu.updateTable(editingHandler,queryRequestHandler,ui);}
                case 5 -> {menuHandler.leasingAgreementMenu.deleteFromTable(editingHandler,queryRequestHandler,ui);}
                case 6 -> {menuHandler.leasingAgreementMenu.getAvailableCars(queryRequestHandler, ui);}
                case 0 -> {isRunning = false;}
                default -> System.out.println(ui.invalidChoiceInput());
            } // End of switch case
        } // End of while loop
    } // End of method

    private void runCarRegistryMenu() {
        boolean isRunning = true;
        while (isRunning) {
            menuHandler.carRegistryMenu.printMenu();
            switch (ui.readInteger()) {
                case 1 -> {menuHandler.carRegistryMenu.showTable(queryRequestHandler);}
                case 2 -> {menuHandler.carRegistryMenu.showTableOrdered(queryRequestHandler);}
                case 3 -> {menuHandler.carRegistryMenu.insertToTable(editingHandler, queryRequestHandler,ui);}
                case 4 -> {menuHandler.carRegistryMenu.updateTable(editingHandler, queryRequestHandler, ui);}
                case 5 -> menuHandler.carRegistryMenu.deleteFromTable(editingHandler, queryRequestHandler, ui);
                case 0 -> isRunning = false;
                default -> System.out.println(ui.invalidChoiceInput());
            } // End of switch case
        } // End of while loop
    } // End of method

    public void runCarPropertyMenu() {
        boolean isRunning = true;
        while (isRunning) {
            menuHandler.carPropertiesMenu.printMenu();
            switch (ui.readInteger()) {
                case 1 -> menuHandler.carPropertiesMenu.showTable(queryRequestHandler);
                case 2 -> menuHandler.carPropertiesMenu.showTableOrdered(queryRequestHandler);
                case 3 -> menuHandler.carPropertiesMenu.searchAndShowTable(queryRequestHandler, ui);
                case 4 -> menuHandler.carPropertiesMenu.updateTable(editingHandler,queryRequestHandler,ui);
                case 5 -> menuHandler.carPropertiesMenu.deleteFromTable(editingHandler,queryRequestHandler,ui);
                case 0 -> isRunning = false;
                default -> System.out.println(ui.invalidChoiceInput());
            } // End of switch case
        } // End of while loop
    } // End of method


    private void runCarInfoMenu() {

    }

    private void runAnalysisMenu() {
        boolean isRunning = true;
        while (isRunning) {
            menuHandler.analysisMenu.printMenu();
            switch (ui.readInteger()) {
                case 1 -> {menuHandler.analysisMenu.showTable(queryRequestHandler);}
                case 2 -> {menuHandler.analysisMenu.showTableOrdered(queryRequestHandler);}
                case 3 -> {menuHandler.analysisMenu.insertToTable(editingHandler, queryRequestHandler,ui);}
                case 0 -> {isRunning = false;}
                default -> System.out.println(ui.invalidChoiceInput());
            } // End of switch case
        } // End of while loop
    } // End of method

}