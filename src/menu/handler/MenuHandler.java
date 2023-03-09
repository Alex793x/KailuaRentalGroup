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
            "1. ",
            "2. ",
            "3. ",
            "4. "
    });

    public AnalysisMenu analysisMenu = new AnalysisMenu("ANALYSIS MENU", new String[] {
            "1. Show best customers in descending order",
            "2. Show occupancy by car group",
            "3. Show occupancy by month",
            "4. "
    });

    public CarInfoMenu carInfoMenu = new CarInfoMenu("CAR INFO MENU", new String[] {
            "1. ",
            "2. ",
            "3. ",
            "4. "
    });

    public CustomerMenu customerMenu = new CustomerMenu("CUSTOMER MENU", new String[] {
            "1. ",
            "2. ",
            "3. ",
            "4. "
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