package dbm.handler;

import dbm.DB_Dependencies;

import java.sql.*;

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

    public String getColumnDataType(String tableName, String columnName) {
        try (java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_name", "user", "password")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + tableName +"' AND COLUMN_NAME = '" + columnName + "'");

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

}
