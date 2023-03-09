package dbm;

public class DB_Dependencies {

    private static DB_Dependencies theInstance = new DB_Dependencies();

    // Login / Connection Fields ---------------------------------------------------------
    public final String database_url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7603879";
    public final String username = "sql7603879";
    public final String password = "kp1ScwPK48";

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