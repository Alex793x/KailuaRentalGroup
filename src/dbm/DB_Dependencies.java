package dbm;

public class DB_Dependencies {

    private static DB_Dependencies theInstance = new DB_Dependencies();

    // Login / Connection Fields ---------------------------------------------------------
    public final String database_url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7603879";
    public final String username = "sql7603879";
    public final String password = "kp1ScwPK48";

    public final String[] CUSTOMER_COLUMNS = {"customer_id", "customer_name", "customer_address", "customer_zip",
            "customer_city", "customer_phone", "customer_email", "customer_driver_license_number",
            "customer_driver_since"};

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