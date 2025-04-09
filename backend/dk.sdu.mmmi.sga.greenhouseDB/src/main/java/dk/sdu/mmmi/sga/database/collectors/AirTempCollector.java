package dk.sdu.mmmi.sga.database.collectors;

import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.models.AirTemperature;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirTempCollector extends DatabaseConnection implements DataCollection<AirTemperature> {

    public AirTempCollector() {
        super("C:/Users/Vandp/Desktop/Universitet/GreenhouseDB");
    }

    @Override
    public String getName() {
        return "Air Temperature";
    }

    @Override
    public List<AirTemperature> collect() {
        List<AirTemperature> results = new ArrayList<>();
        try (ResultSet rs = queryExecution("SELECT * FROM APP.AIR_TEMPERATURE")) {
            while (rs.next()) {
                results.add(new AirTemperature(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("CELCIUS")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void close() {
        try {
            if (connection != null){ connection.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
