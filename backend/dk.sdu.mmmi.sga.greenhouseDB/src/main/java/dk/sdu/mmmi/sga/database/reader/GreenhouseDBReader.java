package dk.sdu.mmmi.sga.database.reader;

import java.sql.*;
import java.util.*;

public class GreenhouseDBReader {

    private final String url = "jdbc:derby://localhost:1527/GreenhouseDB;create=false";
    private final String user = "user1";
    private final String pass = "user1";

    public List<Map<String, Object>> fetchData(String tableName) {
        List<Map<String, Object>> results = new ArrayList<>();
        String query = "SELECT * FROM APP." + tableName + " ORDER BY TIME ASC FETCH FIRST 100 ROWS ONLY";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                results.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public static void main(String[] args) {
        GreenhouseDBReader collector = new GreenhouseDBReader();
        List<Map<String, Object>> data = collector.fetchData("AIR_TEMPERATURE");
        data.forEach(System.out::println);
        data = collector.fetchData("HUMIDITY");
        data.forEach(System.out::println);
    }
}