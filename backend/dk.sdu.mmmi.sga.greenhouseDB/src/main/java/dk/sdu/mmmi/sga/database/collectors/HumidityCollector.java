package dk.sdu.mmmi.sga.database.collectors;

import dk.sdu.mmmi.sga.core.dto.HumidityDTO;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HumidityCollector extends DatabaseConnection implements DataCollection<HumidityDTO> {
    public HumidityCollector() {
        super();
    }

    @Override
    public String getName() {
        return "Humidity";
    }

    @Override
    public List<HumidityDTO> collect() {
        List<HumidityDTO> results = new ArrayList<>();
        try (ResultSet rs = queryExecution("HUMIDITY")) {
                while (rs.next()) {
                    HumidityDTO humidityDTO = new HumidityDTO(
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
