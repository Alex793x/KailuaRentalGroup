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
            "3. Cars Properties",
            "4. Leasing agreement",
            "5. Rental Service",
            "6. Analysis",
            "0. Exit"
    });

    public AnalysisMenu analysisMenu = new AnalysisMenu("ANALYSIS MENU", new String[] {
            "1. Show best customers in descending order",
            "2. Show occupancy by car group",
            "3. Show occupancy by month",
            "0. Back to main menu"
    });

    public CarRegistryMenu carRegistryMenu = new CarRegistryMenu("CAR INFO MENU", new String[] {
            "1. Show all cars",
            "2. Show all cars ordered",
            "3. Add new car",
            "4. Edit car registry",
            "5. Delete car registry",
            "0. Back to main menu"
    });

    public CarPropertiesMenu carPropertiesMenu = new CarPropertiesMenu("CAR PROPERTY MENU", new String[] {
            "1. Show all property combinations",
            "2. Show all property combinations ordered",
            "3. Search specific and show results",
            "4. Edit property for car",
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
            "1. Show all leasing agreements",
            "2. Show all leasing agreements ordered",
            "3. Add new leasing agreement",
            "4. Edit leasing agreement",
            "5. Delete leasing agreement",
            "6. Find available Cars"
    });

    public CarInfoMenu rentalServiceMenu = new CarInfoMenu("RENTAL SERVICE MENU", new String[] {
            "1. ",
            "2. ",
            "3. ",
            "4. "
    });
}