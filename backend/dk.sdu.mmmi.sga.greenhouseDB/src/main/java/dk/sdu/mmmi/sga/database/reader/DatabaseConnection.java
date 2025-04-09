package dk.sdu.mmmi.sga.database.reader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseConnection {
    protected Connection connection;

    public DatabaseConnection(String dbPath){
        try {
            String url = "jdbc:derby:" + dbPath + ";create=false";
            this.connection = java.sql.DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public abstract void close();

    protected ResultSet queryExecution(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }
}
