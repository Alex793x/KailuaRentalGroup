package controller;

import dbm.handler.DB_QueryEditingHandler;
import dbm.handler.DB_QueryRequestHandler;
import utility.*;
import menu.handler.*;

/**
 * The AppLogic class is defining and running all the logic within the app.
 * Think about it as the controller of the whole application, requesting and handling queries
 * from each class.
 * <p>In essence different menus will be calling queries on the database for the Kailua Rental Group.</p>
 * @implNote {@link #runMainMenu()} Will be showing the different sections in categorized fashion. <br>
 * {@link #runCustomerMenu()} Will be handling all customer queries (adding new customer, delete, edit etc.) <br>
 * {@link #runLeasingAgreementMenu()} Will be handling all leasing agreements while query details on cars and specifications <br>
 * {@link #runCarInfoMenu()} Will be handling all queries when user wants to create unique search criteria on car info <br>
 * {@link #runRentalServiceMenu()} Will be handling payments, setups, while also other important employee queries <br>
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
                case 2 -> runCarInfoMenu();
                case 3 -> runLeasingAgreementMenu();
                case 4 -> runRentalServiceMenu();
                case 5 -> runAnalysisMenu();
                case 0 -> isRunning = false;
                default -> System.out.println("invalid input, please try again");
            } // End of switch case
        } // End of while loop
    } // End of method

    private void runCustomerMenu() {
        // TODO - implement controller.AppLogic.runCustomerMenu
        throw new UnsupportedOperationException();
    }

    private void runLeasingAgreementMenu() {
        // TODO - implement controller.AppLogic.runLeasingAgreementMenu
        throw new UnsupportedOperationException();
    }

    private void runCarInfoMenu() {
        boolean isRunning = true;
        while (isRunning) {
            menuHandler.carInfoMenu.printMenu();
            switch (ui.readInteger()) {
                case 1 -> {menuHandler.carInfoMenu.showTable(queryRequestHandler);}
                case 2 -> {menuHandler.carInfoMenu.showTableOrdered(queryRequestHandler);}
                case 3 -> {menuHandler.carInfoMenu.insertToTable(editingHandler, ui);}
            }
        }
    }

    private void runRentalServiceMenu() {
        // TODO - implement controller.AppLogic.runRentalServiceMenu
        throw new UnsupportedOperationException();
    }

    private void runAnalysisMenu() {
        boolean isRunning = true;
        while (isRunning) {
            menuHandler.analysisMenu.printMenu();
            switch (ui.readInteger()) {
                case 1 -> {menuHandler.analysisMenu.showTable(queryRequestHandler);}
                case 2 -> {menuHandler.analysisMenu.showTableOrdered(queryRequestHandler);}
                case 3 -> {menuHandler.analysisMenu.insertToTable(editingHandler, ui);}
                case 0 -> {isRunning = false;}
            }
        }

    }

}