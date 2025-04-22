package dk.sdu.mmmi.sga.database.collectors;

import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.models.AirTemperature;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirTempCollector extends DatabaseConnection implements DataCollection<AirTemperature> {

    private int i = 1;
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
        try (ResultSet rs = queryExecution("SELECT * FROM APP.AIR_TEMPERATURE ORDER BY TIME ASC FETCH FIRST "+i+" ROWS ONLY")) {
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
        i++;
        if (i > 100) i--;
        return results;
    }
}
