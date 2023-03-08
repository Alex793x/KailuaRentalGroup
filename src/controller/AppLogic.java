package controller;

import utility.*;
import menu.handler.*;

/**
 * The AppLogic class is defining and running all the logic within the app.
 * Think about it as the controller of the whole application, requesting and handling queries
 * from each class.
 * <p>In essence different menus will be calling queries on the database for the Kailua Rental Group.</p>
 * @implNote {@link #runMainMenu()} Will be showing the different sections in categorized fashion.
 * {@link #runCustomerMenu()} Will be handling all customer queries (adding new customer, delete, edit etc.)
 * {@link #runLeasingAgreementMenu()} Will be handling all leasing agreements while query details on cars and specifications
 * {@link #runCarInfoMenu()} Will be handling all queries when user wants to create unique search criteria on car info
 * {@link #runRentalServiceMenu()} Will be handling payments, setups, while also other important employee queries
 * {@link #runAnalysisMenu()} Will be handling detailed analysis queries if employees want to get some detailed information about customer behaviour
 */
public class AppLogic {
    // Fields ----------------------------------------------------------------------------
    private final UI ui;
    private final MenuHandler menuHandler;


    // Constructor -----------------------------------------------------------------------

    /**
     * The AppLogic constructor.
     */
    public AppLogic() {
        ui = new UI();
        menuHandler = new MenuHandler();
    }


    // Methods ---------------------------------------------------------------------------

    /**
     *
     */
    private void runMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
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
        // TODO - implement controller.AppLogic.runCarInfoMenu
        throw new UnsupportedOperationException();
    }

    private void runRentalServiceMenu() {
        // TODO - implement controller.AppLogic.runRentalServiceMenu
        throw new UnsupportedOperationException();
    }

    private void runAnalysisMenu() {
        // TODO - implement controller.AppLogic.runAnalysisMenu
        throw new UnsupportedOperationException();
    }

}