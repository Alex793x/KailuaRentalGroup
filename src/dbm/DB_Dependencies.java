package dbm;

public class DB_Dependencies {

    private static DB_Dependencies theInstance = new DB_Dependencies();

    // Login / Connection Fields ---------------------------------------------------------
    public final String database_url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7603879";
    public final String username = "sql7603879";
    public final String password = "kp1ScwPK48";

    public final String[] tableNames = {
            "customer_info", "car_properties", "rental_registry", "car_registry", "car_rental_groups"
    };

    public final String[] carRegistry = {
            "car_registry_id", "car_brand", "car_model", "registration_number", "first_registration",
            "availability", "car_rental_group_id"
    };

    public final String[] carRegistryColumnPrintFormat = {
            "Car registry id: ", "Car brand: ", "Car model: ", "Registration number: ", "First registration",
            "Availability: ", "Car rental group id: "
    };





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