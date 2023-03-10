package dbm;

public class DB_Dependencies {

    private static DB_Dependencies theInstance = new DB_Dependencies();

    // Login / Connection Fields ---------------------------------------------------------
    public final String database_url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7603879";
    public final String username = "sql7603879";
    public final String password = "kp1ScwPK48";



    // Table INFO and Dependencies
    /**
     * <p>The {@link #TABLE_NAMES} includes all table names within the database</p>
     * @implNote 0 = customer_info <br>
     * 1 = car_properties <br>
     * 2 = rental_registry <br>
     * 3 = car_registry <br>
     * 4 = car_rental_groups
     */
    public final String[] TABLE_NAMES = {
            "customer_info", "car_properties", "rental_registry", "car_registry", "car_rental_groups"
    };


    /**
     * <p>The {@link #CAR_REGISTRY_COLUMNS} includes all columns within the database which is including within the table car_registry.</p>
     * @implNote 0 = car_registry_id <br>
     * 1 = car_brand <br>
     * 2 = car_model <br>
     * 3 = registration_number <br>
     * 4 = first_registration <br>
     * 5 = availability <br>
     * 6 = car_rental_group_id
     */
    // Car registry section --------------------------------------------------------------
    public final String[] CAR_REGISTRY_COLUMNS = {
            "car_brand", "car_model", "registration_number", "first_registration",
            "availability", "car_rental_group_id", "car_isRented"

    };

    /**
     * <p>The {@link #CAR_REGISTRY_COLUMN_PRINT_FORMAT} is simply a nicer formatting for the column names for printing purposes within the console
     * please look at {@link #CAR_REGISTRY_COLUMNS} for further details of content</p>
     */
    public final String[] CAR_REGISTRY_COLUMN_PRINT_FORMAT = {
            "Car brand: ", "Car model: ", "Registration number: ", "First registration",
            "Availability: ", "Car rental group id: ","Car Is Rented?: "
    };

    // Customer section ------------------------------------------------------------------
    /**
     * <p>The {@link #CUSTOMER_COLUMNS} includes all columns within the database which is including within the table customer_info.</p>
     * @implNote 0 = customer_id <br>
     * 1 = customer_name <br>
     * 2 = customer_address <br>
     * 3 = customer_zip <br>
     * 4 = customer_city <br>
     * 5 = customer_phone <br>
     * 6 = customer_email <br>
     * 7 = customer_driver_license_number <br>
     * 8 = customer_driver_since <br>
     */
    public final String[] CUSTOMER_COLUMNS = {"customer_id", "customer_name", "customer_address", "customer_zip",
            "customer_city", "customer_phone", "customer_email", "customer_driver_license_number",
            "customer_driver_since"};


    // Leasing Agreements section --------------------------------------------------------
    public final String[] LEASING_AGREEMENT_COLUMN_PRINT_FORMAT = {"Rental Registry ID: ", "Rental Start Date: ",
            "Rental End Date: ", "Car Registry ID: ","Customer ID: "};

    public final String[] LEASING_AGREEMENT_COLUMNS = {"rental_registry_id", "rental_start_date", "rental_end_date",
            "car_registry_id", "customer_id"};




    // Car Rental Group Section ----------------------------------------------------------
    public final String[] CAR_RENTAL_GROUP_PRINT_FORMAT = {"Car Rental Group ID: ", "Car Rental Group Name: ",
            "Car Properties ID: "};

    public final String[] CAR_RENTAL_GROUPS_COLUMNS = {"car_rental_group_id", "car_rental_group_name", "car_properties_id"};



    // Constructor -----------------------------------------------------------------------
    private DB_Dependencies() {}

    // Getter ----------------------------------------------------------------------------
    public static DB_Dependencies getInstance() {
        if (theInstance == null) {
            theInstance = new DB_Dependencies();
        }
        return theInstance;
    }
}