package menu.handler;

import menu.sub_menus.*;
/**
 * The MenuHandler class is instantiating every sub menu within the app,
 * while also being the only entity which can request certain actions happening
 * from each menu.
 */
public class MenuHandler {

    // Constructor ----------------------------------------------------------------
    /**
     * MenuHandler constructor
     */
    public MenuHandler() {

    }


    // Menus being instantiated ----------------------------------------------------

    public MainMenu mainMenu = new MainMenu("MAIN MENU", new String[] {
            "1. Customers",
            "2. Cars",
            "3. Leasing agreement",
            "4. Rental Service",
            "5. Analysis"
    });

    public AnalysisMenu analysisMenu = new AnalysisMenu("ANALYSIS MENU", new String[] {
            "1. Show best customers in descending order",
            "2. Show occupancy by car group",
            "3. Show occupancy by month",
            "4. "
    });

    public CarInfoMenu carInfoMenu = new CarInfoMenu("CAR INFO MENU", new String[] {
            "1. Show all cars.",
            "2. Show all cars ordered",
            "3. Add new car",
            "4. Edit car registry",
            "5. Delete car registry",
            "0. Back to main menu"
    });

    public CustomerMenu customerMenu = new CustomerMenu("CUSTOMER MENU", new String[] {
            "1. Show all customers.",
            "2. Show all customers ordered.",
            "3. Add new customer.",
            "4. Edit customer info",
            "5. Delete customer",
            "0. Return to main menu"
    });

    public LeasingAgreementMenu leasingAgreementMenu = new LeasingAgreementMenu("LEASING AGREEMENT MENU", new String[] {
            "1. ",
            "2. ",
            "3. ",
            "4. "
    });

    public RentalServiceMenu rentalServiceMenu = new RentalServiceMenu("RENTAL SERVICE MENU", new String[] {
            "1. ",
            "2. ",
            "3. ",
            "4. "
    });
}