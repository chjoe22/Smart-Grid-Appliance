package dk.sdu.mmmi.sga.database.reader;

import dk.sdu.mmmi.sga.database.model.AirTemperature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GreenhouseDBReader {
    // for now, should use the absolute path to where you have the database loacated
    // The database should be made remote so we can use a localhost connection preferably localhost:1557 if correct
    private final String url = "jdbc:derby:C:/Users/Vandp/Desktop/Universitet/GreenhouseDB;create=false";
    private final String user = "user1";
    private final String pass = "user1";


    public List<AirTemperature> readAirTemperature() {
        List<AirTemperature> airTemperatureList = new ArrayList<>();
        String query = "SELECT * FROM APP.AIR_TEMPERATURE";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                int contextId = rs.getInt("CONTEXT_ID");
                Timestamp timestamp = rs.getTimestamp("TIME");
                double celsius = rs.getDouble("CELCIUS");

                airTemperatureList.add(new AirTemperature(id, contextId, timestamp, celsius));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return airTemperatureList;
    }

    public static void main(String[] args) {

        GreenhouseDBReader reader = new GreenhouseDBReader();
        List<AirTemperature> results = reader.readAirTemperature();
        results.forEach(System.out::println);
    }
}
