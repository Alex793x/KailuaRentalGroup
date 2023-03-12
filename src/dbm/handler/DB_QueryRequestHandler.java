package dbm.handler;

import dbm.DB_Dependencies;
import dbm.interfaces.query_interfaces.DBStandardQueries;
import utility.UI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

public class DB_QueryRequestHandler {

    /**
     * This method queries the DB to figure out the data type. This is to make sure the correct UI input method
     * for the requested change matches the correct data ype to be processed within the DB.
     *
     * @param tableName The SQL Schema table
     * @param columnName The column name of specific Table / Schema
     * @return Returns if data-type of the queried column is {int, varchar, tinyint, date, double etc.}
     */
    public String getColumnDataType(String tableName, String columnName) {
        String dataTypeQuery = "SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + tableName +"' AND COLUMN_NAME = '" + columnName + "'";
        try (
                java.sql.Connection connection = DriverManager.getConnection(
                DB_Dependencies.getInstance().database_url,
                DB_Dependencies.getInstance().username,
                DB_Dependencies.getInstance().password);

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(dataTypeQuery)
        ) {

            if (resultSet.next()) {
                return resultSet.getString("DATA_TYPE");
            } else {
                System.out.println("Error: Column not found in the table");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving the data type of the column: " + e.getMessage());
            return null;
        }
    }

    /**
     * This method is a generic way to print out the result from a SQL query
     * @param query The SQL statement which queries the DB
     * @param printColumns  The formatted print version for console of the columns from SQL query
     * @param dbColumnNames The exact column names which the DB columns of the table are named
     * @implNote Whenever a request for print is needed, this method should be called with given three arguments.
     */
    public void printQueryResult(String query, String[] printColumns, String[] dbColumnNames) {
        try (
                Connection connection = DriverManager.getConnection(
                        DB_Dependencies.getInstance().database_url,
                        DB_Dependencies.getInstance().username,
                        DB_Dependencies.getInstance().password
                );

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            printResultSet(resultSet, printColumns, dbColumnNames);
        } catch (SQLException e) {
            System.out.println("Error with SQL Print request");
        } // End of try - catch block
    } // End of method

    private void printResultSet(ResultSet resultSet, String[] printColumns, String[] dbColumnNames) throws SQLException {
        // Get metadata
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numColumns = metaData.getColumnCount();

        // Print column headers
        for (String column : printColumns) {
            System.out.printf("%-30s","[" + column + "]");
        } // End of for loop
        System.out.println();

        // Print rows
        while (resultSet.next()) {
            for (int i = 1; i <= numColumns; i++) {
                Object value = resultSet.getObject(i);
                System.out.printf("%-30s", value);
            } // End of for loop
            System.out.println();
        } // End of while loop
        System.out.println();
    } // End of method

    public boolean checkIfExists(String query, int valueID, int columnForID) {
        try (
                Connection connection = DriverManager.getConnection(
                        DB_Dependencies.getInstance().database_url,
                        DB_Dependencies.getInstance().username,
                        DB_Dependencies.getInstance().password
                );

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                System.out.println(resultSet.getObject(columnForID));
                if (resultSet.getObject(columnForID).equals(valueID)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error with SQL Print request");
        } // End of try - catch block
        return false;
    } // End of method
}
