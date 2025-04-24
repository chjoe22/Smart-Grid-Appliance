package dk.sdu.mmmi.sga.database.reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DatabaseConnection {
    protected Connection connection;
    private int i = 1;

    protected DatabaseConnection(){
        try {
            //String url = "jdbc:derby:C:/Users/Vandp/Desktop/Universitet/GreenhouseDB;create=false";
            String url = "jdbc:derby://localhost:1527/GreenhouseDB;create=false";
            this.connection = java.sql.DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public List<Map<String, Object>> fetchData(String tableName) {
        List<Map<String, Object>> results = new ArrayList<>();

        // Imitates behaviour of polling as "i" increases by 1 each time the Spring scheduler runs
        String query = "SELECT * FROM APP." + tableName + " ORDER BY TIME ASC FETCH FIRST "+i+" ROWS ONLY";

        // Tries to execute the query and fetch the results
        try (ResultSet rs = queryExecution(query)) {

            // Get metadata to determine the number of columns
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int counter = 1; counter <= columnCount; counter++) {
                    String columnName = metaData.getColumnName(counter);
                    Object value = rs.getObject(counter);
                    row.put(columnName, value);
                }
                results.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        i++;
        if (i > 100) i--;
        return results;
    }

    protected ResultSet queryExecution(String tableName) throws SQLException {
        Statement stmt = connection.createStatement();
        String queryString = "SELECT * FROM APP."+ tableName +" ORDER BY TIME ASC FETCH FIRST "+i+" ROWS ONLY";
        i = Math.min(i + 1, 100);
        return stmt.executeQuery(queryString);
    }
}
