package dbm;

public class DB_Dependencies {

    private static DB_Dependencies theInstance = new DB_Dependencies();

    // Login / Connection Fields ---------------------------------------------------------
    public final String database_url = "jdbc:mysql://localhost:3306/kailua_rental";
    public final String username = "root";
    public final String password = "Mfbz9yzxn";


    // Table INFO and Dependencies ------------------------------------------------------
    /**
     * <p>The {@link #TABLE_NAMES} includes all table names within the database</p>
     *
     * @implNote 0 = customer_info <br>
     * 1 = car_properties <br>
     * 2 = rental_registry <br>
     * 3 = car_registry <br>
     * 4 = car_rental_groups
     */
    public final String[] TABLE_NAMES = {
            "customer_info", "car_properties", "rental_registry", "car_registry", "car_rental_groups"
    };


    // Car registry section --------------------------------------------------------------
    /**
     * <p>The {@link #CAR_REGISTRY_COLUMNS} includes all columns within the database which is including within the table car_registry.</p>
     *
     * @implNote 0 = car_registry_id <br>
     * 1 = car_brand <br>
     * 2 = car_model <br>
     * 3 = registration_number <br>
     * 4 = first_registration <br>
     * 5 = availability <br>
     * 6 = car_rental_group_id
     */
    public final String[] CAR_REGISTRY_COLUMNS = {
            "car_registry_id", "car_brand", "car_model", "registration_number", "first_registration",
            "availability", "car_rental_group_id", "car_isRented"

    };

    /**
     * <p>The {@link #CAR_REGISTRY_COLUMN_PRINT_FORMAT} is simply a nicer formatting for the column names for printing purposes within the console
     * please look at {@link #CAR_REGISTRY_COLUMNS} for further details of content</p>
     *
     * @implNote 0 = Car Registry ID <br>
     * 1 = Car brand <br>
     * 2 = Car model <br>
     * 3 = Registration number <br>
     * 4 = First registration <br>
     * 5 = Availability <br>
     * 6 = Car rental group id
     * 7 = Car Is Rented ?
     */
    public final String[] CAR_REGISTRY_COLUMN_PRINT_FORMAT = {
            "Car Registry ID", "Car brand", "Car model", "Registration number", "First registration",
            "Availability", "Car rental group id", "Car Is Rented ?"
    };


    // Car properties section ------------------------------------------------------------
    /**
     * @implNote 0 = car_properties_id <br>
     * 1 = ccm <br>
     * 2 = gear_type <br>
     * 3 = air_condition <br>
     * 4 = seat_type <br>
     * 5 = seat_amount <br>
     * 6 = horsepower <br>
     * 7 = cruise_control <br>
     * 8 = odometer <br>
     * 9 = fuel_type
     */
    public final String[] CAR_PROPERTIES_COLUMNS = {
            "car_properties_id", "ccm", "gear_type", "air_condition", "seat_type", "seat_amount",
            "horsepower", "cruise_control", "odometer", "fuel_type"
    };


    /**
     * <p>The {@link #CAR_PROPERTIES_PRINT_FORMAT} is simply a nicer formatting for the column names for printing purposes within the console </p>
     *
     * @implNote 0 = car_properties_id <br>
     * 1 = ccm <br>
     * 2 = gear_type <br>
     * 3 = air_condition <br>
     * 4 = seat_type <br>
     * 5 = seat_amount <br>
     * 6 = horsepower <br>
     * 7 = cruise_control <br>
     * 8 = odometer <br>
     * 9 = fuel_type
     */
    public final String[] CAR_PROPERTIES_PRINT_FORMAT = {
            "Car Properties ID", "CCM", "Gear Type", "Air Condition", "Seat Type", "Seat Amount",
            "Horsepower", "Cruise Control", "Odometer", "Fuel Type"
    };


    // Customer section ------------------------------------------------------------------
    /**
     * <p>The {@link #CUSTOMER_COLUMNS} includes all columns within the database which is including within the table customer_info.</p>
     *
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
    public final String[] CUSTOMER_COLUMNS = {"customer_id", "customer_name",
            "customer_address", "customer_zip", "customer_city", "customer_phone", "customer_email",
            "customer_driver_license_number", "customer_driver_since"};


    //Analysis section ---------------------------------------
    /**
     * <p>The {@link #bestCustomerPrint} includes all columns within the database which is including within the table customer_info.</p>
     *
     * @implNote 0 = Customer Name <br>
     * 1 = Customer ID <br>
     * 2 = Total Days Rented <br>
     */
    public final String[] bestCustomerPrint = {"Customer Name", "Customer ID", "Total Days Rented"};

    /**
     * <p>The {@link #BestCustomerDB} includes all columns within the database which is including within the table customer_info.</p>
     *
     * @implNote 0 = customer_name <br>
     * 1 = customer_id <br>
     * 2 = total_days_rented <br>
     */
    public final String[] BestCustomerDB = {"customer_name", "customer_id", "total_days_rented"};


    // Registry section --------------------------------------
    /**
     * <p>The {@link #rentalRegistryCOLUMNS} includes all columns within the database which is including within the table customer_info.</p>
     *
     * @implNote 0 = rental_registry.rental_registry_id <br>
     * 1 = rental_registry.rental_start_date <br>
     * 2 = rental_registry.rental_end_date <br>
     * 3 = rental_registry.car_registry_id <br>
     * 4 = rental_registry.customer_id <br>
     */
    public final String[] rentalRegistryCOLUMNS = {
            "rental_registry.rental_registry_id", "rental_registry.rental_start_date", "rental_registry.rental_end_date"
            , "rental_registry.car_registry_id", "rental_registry.customer_id"};


    // Leasing Agreements section --------------------------------------------------------
    /**
     * <p>The {@link #rentalRegistryCOLUMNS} includes all columns within the database which is including within the table customer_info.</p>
     *
     * @implNote 0 = Rental Registry ID <br>
     * 1 = Rental Start Date <br>
     * 2 = Rental End Date <br>
     * 3 = Car Registry ID <br>
     * 4 = Customer ID <br>
     */
    public final String[] LEASING_AGREEMENT_COLUMN_PRINT_FORMAT = {"Rental Registry ID", "Rental Start Date",
            "Rental End Date", "Car Registry ID", "Customer ID"};

    /**
     * <p>The {@link #rentalRegistryCOLUMNS} includes all columns within the database which is including within the table customer_info.</p>
     *
     * @implNote 0 = rental_registry_id <br>
     * 1 = rental_start_date <br>
     * 2 = rental_end_date <br>
     * 3 = car_registry_id <br>
     * 4 = customer_id <br>
     */
    public final String[] LEASING_AGREEMENT_COLUMNS = {"rental_registry_id", "rental_start_date", "rental_end_date",
            "car_registry_id", "customer_id"};


    // Car Rental Group Section ----------------------------------------------------------
    /**
     * <p>The {@link #rentalRegistryCOLUMNS} includes all columns within the database which is including within the table customer_info.</p>
     *
     * @implNote 0 = Car Rental Group ID <br>
     * 1 = Car Rental Group Name <br>
     * 2 = Car Properties ID <br>
     */
    public final String[] CAR_RENTAL_GROUP_PRINT_FORMAT = {"Car Rental Group ID: ", "Car Rental Group Name",
            "Car Properties ID"};

    /**
     * <p>The {@link #rentalRegistryCOLUMNS} includes all columns within the database which is including within the table customer_info.</p>
     *
     * @implNote 0 = car_rental_group_id <br>
     * 1 = car_rental_group_name <br>
     * 2 = car_properties_id <br>
     */
    public final String[] CAR_RENTAL_GROUPS_COLUMNS = {"car_rental_group_id", "car_rental_group_name", "car_properties_id"};


    // Constructor -----------------------------------------------------------------------
    private DB_Dependencies() {
    }

    // Getter ----------------------------------------------------------------------------
    public static DB_Dependencies getInstance() {
        if (theInstance == null) {
            theInstance = new DB_Dependencies();
        }
        return theInstance;
    }
}