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

/*
    public void printByLength(String query, String[] printColumns, String[] dbColumnName) {
        try (
                java.sql.Connection connection = DriverManager.getConnection(
                        DB_Dependencies.getInstance().database_url,
                        DB_Dependencies.getInstance().username,
                        DB_Dependencies.getInstance().password);

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            switch (printColumns.length) {
                case 3 -> printLengthThree(resultSet, printColumns, dbColumnName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

 */


    // IKKE GENERISK PRINTMETODE TIL ANALYSE JOIN
/*
    public void printLengthThree(String query, String[] printColumns, String[] dbColumnName) {
        try (
                java.sql.Connection connection = DriverManager.getConnection(
                        DB_Dependencies.getInstance().database_url,
                        DB_Dependencies.getInstance().username,
                        DB_Dependencies.getInstance().password);

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {

            System.out.printf("%-20s %-17s %-17s%n", printColumns[0], printColumns[1], printColumns[2]);
            while (resultSet.next()) {
                System.out.printf("%-20s %-17s %-17s%n", resultSet.getString(dbColumnName[0]),
                        resultSet.getString(dbColumnName[1]), resultSet.getString(dbColumnName[2]));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

 */
   /*
    private void printLengthThree(ResultSet resultSet, String[] printColumns, String[] dbColumnName) {
        System.out.printf("%-20s %-17s %-17s%n", printColumns[0], printColumns[1], printColumns[2]);
        while (resultSet.next()) {
            System.out.printf("%-20s %-17s %-17s%n", resultSet.getString(dbColumnName[0]),
                    resultSet.getString(dbColumnName[1]), resultSet.getString(dbColumnName[2]));

    }

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
            handleSQLException(e);
        }
    }

    private void printResultSet(ResultSet resultSet, String[] printColumns, String[] dbColumnNames) throws SQLException {
        // Get metadata
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numColumns = metaData.getColumnCount();

        // Print column headers
        for (String column : printColumns) {
            System.out.printf("%-20s", column);
        }
        System.out.println();

        // Print rows
        while (resultSet.next()) {
            for (int i = 1; i <= numColumns; i++) {
                Object value = resultSet.getObject(i);
                System.out.printf("%-20s", value);
            }
            System.out.println();
        }
    }

    private void handleSQLException(SQLException e) {
        throw new RuntimeException(e);
    }
}