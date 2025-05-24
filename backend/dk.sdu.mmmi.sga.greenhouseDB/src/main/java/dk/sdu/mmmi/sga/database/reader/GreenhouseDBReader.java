package dk.sdu.mmmi.sga.database.reader;

import dk.sdu.mmmi.sga.db.util.DBConn;

import java.sql.*;
import java.util.*;

public class GreenhouseDBReader extends DBConn {

    private int lastSeenId = 0;

    public List<Map<String, Object>> fetchNewData(String tableName) {
        return incrementQueryExecution(tableName, 100, (ResultSet rs) -> {
            List<Map<String, Object>> results = new ArrayList<>();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }

                // Update lastSeenId if column exists
                try {
                    Object idObj = row.get("ID");
                    if (idObj instanceof Number id) {
                        lastSeenId = Math.max(lastSeenId, id.intValue());
                    }
                } catch (Exception ignore) {}

                results.add(row);
            }

            return results;
        });
    }

    public static void main(String[] args) {
        GreenhouseDBReader reader = new GreenhouseDBReader();

        System.out.println("--- AIR_TEMPERATURE ---");
        reader.fetchNewData("AIR_TEMPERATURE").forEach(System.out::println);

    }
}
