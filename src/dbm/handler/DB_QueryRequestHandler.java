package dbm.handler;

import dbm.DB_Dependencies;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_QueryRequestHandler {

    public void printQuery(String query, String[] printColumns, String[] dbColumnName) {
        try (
                java.sql.Connection connection = DriverManager.getConnection(
                        DB_Dependencies.getInstance().database_url,
                        DB_Dependencies.getInstance().username,
                        DB_Dependencies.getInstance().password);

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet != null) {
                while (resultSet.next()) {
                    for (int i = 0; i < printColumns.length; i++) {
                        System.out.print(printColumns[i] + resultSet.getString(dbColumnName[i]) + ". ");
                    }
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // IKKE GENERISK PRINTMETODE TIL JOINS
    public void printALIAS(String query, String[] printColumns, String[] dbColumnName) {
        try (
                java.sql.Connection connection = DriverManager.getConnection(
                        DB_Dependencies.getInstance().database_url,
                        DB_Dependencies.getInstance().username,
                        DB_Dependencies.getInstance().password);

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet != null) {
                while (resultSet.next()) {
                    String customerName = resultSet.getString("customer_name");
                    int customerID = resultSet.getInt("customer_id");
                    int totalDaysRented = resultSet.getInt("total_days_rented");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
