package dbm;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_QueryHandler {
    

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

}
