package dk.sdu.mmmi.sga.database.collectors;

import dk.sdu.mmmi.sga.core.dto.OutDoorTemperatureDTO;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OutDoorTempCollector extends DatabaseConnection implements DataCollection<OutDoorTemperatureDTO> {

    public OutDoorTempCollector() {
        super();
    }

    @Override
    public String getName() {
        return "OutDoorTemperature";
    }

    @Override
    public List<OutDoorTemperatureDTO> collect() {
        List<OutDoorTemperatureDTO> results = new ArrayList<>();
        try (ResultSet rs = queryExecution("OUTDOOR_TEMPERATURE")){
            while (rs.next()) {
                OutDoorTemperatureDTO outDoorTemperatureDTO = new OutDoorTemperatureDTO(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("CELCIUS")
                );
                results.add(outDoorTemperatureDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
