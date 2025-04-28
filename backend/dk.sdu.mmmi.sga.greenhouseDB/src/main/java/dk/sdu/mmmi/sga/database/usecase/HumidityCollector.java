package dk.sdu.mmmi.sga.database.usecase;

import dk.sdu.mmmi.sga.database.entity.Humidity;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HumidityCollector extends DatabaseConnection implements DataCollection<Humidity> {
    public HumidityCollector() {
        super();
    }

    @Override
    public String getName() {
        return "Humidity";
    }

    @Override
    public List<Humidity> collect() {
        List<Humidity> results = new ArrayList<>();
        try (ResultSet rs = queryExecution("HUMIDITY")) {
                while (rs.next()) {
                    Humidity humidityDTO = new Humidity(
                            rs.getInt("ID"),
                            rs.getInt("CONTEXT_ID"),
                            rs.getTimestamp("TIME"),
                            rs.getDouble("FACTOR")
                    );
                    results.add(humidityDTO);
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}
