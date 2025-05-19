package dk.sdu.mmmi.sga.db.util;

import dk.sdu.mmmi.sga.db.service.ResultSetHandler;

import java.sql.*;

/**
 * This class is responsible for establishing a connection to the database and executing queries.
 * It provides methods to fetch data from specific tables in the database.
 * It merely serves as a utility class for database operations.
 * Should be changed to springs application properties datasource in the future.
 */

public abstract class DBConn {

    //String url = "jdbc:derby:/Users/MadsSigsgaard/Downloads/GreenhouseDB;create=false";
    //String url = "jdbc:derby:C:/Users/Vandp/Desktop/Universitet/GreenhouseDB;create=false";
    private static final String DB_URL = "jdbc:derby://localhost:1527/GreenhouseDB;create=false";
    private static final String DB_USER = "user1";
    // Placeholder for password - should be changed to a secure method of storing passwords
    private static final String DB_PASS = "user1";

    private int i = 1; // Imitate polling

    protected Connection getConn() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    /**
     * Executes a specific query to fetch all data from a table and imitates polling
     * @param table Name of the table to query
     * @return ResultSet of the needed data from the database
     */
    protected <T> T incrementQueryExecution(String table, int maxLimit, ResultSetHandler<T> handler) {
        String query = "SELECT * FROM APP." + table + " ORDER BY ID ASC FETCH FIRST " + i + " ROWS ONLY";

        try (Connection conn = getConn();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            i = Math.min(i + 1, maxLimit);
            return handler.handle(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Executes a specific query to fetch all data from a table
     * @param table Name of the table to query
     * @return ResultSet of the needed data from the database
     */
    protected <T> T timeQueryExecution(String table, int limit, ResultSetHandler<T> handler){
        String query = "SELECT * FROM APP." + table + " ORDER BY TIME DESC FETCH FIRST " + limit + " ROWS ONLY";
        try (Connection conn = getConn();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery())
        {
            return handler.handle(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Closes the given AutoCloseable resources.
     * This method is used to close the connection, statement, and result set.
     * It isn't used yet but is a good practice to have.
     */
    protected void closeConnection(AutoCloseable... close) {
        for (AutoCloseable closeable : close){
            if (closeable != null){
                try {
                    closeable.close();
                } catch (Exception ignored) {}
            }
        }
    }
}

