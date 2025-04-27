package dk.sdu.mmmi.sga.database.usecase;

import dk.sdu.mmmi.sga.database.entity.AirTemperature;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AirTempCollector extends DatabaseConnection implements DataCollection<AirTemperature> {

    public AirTempCollector() {
        super();
    }

    @Override
    public String getName() {
        return "Air Temperature";
    }

    @Override
    public List<AirTemperature> collect() {
        List<AirTemperature> results = new ArrayList<>();
        try (ResultSet rs = queryExecution("AIR_TEMPERATURE")) {
            while (rs.next()) {
                AirTemperature airTemp = new AirTemperature(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("CELCIUS")
                );
                results.add(airTemp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
