package dk.sdu.mmmi.sga.database.usecase;

import dk.sdu.mmmi.sga.database.entity.OutDoorTemperature;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class OutDoorTempCollector extends DatabaseConnection implements DataCollection<OutDoorTemperature> {

    public OutDoorTempCollector() {
        super();
    }

    @Override
    public String getName() {
        return "OutDoorTemperature";
    }

    @Override
    public List<OutDoorTemperature> collect() {
        List<OutDoorTemperature> results = new ArrayList<>();
        try (ResultSet rs = queryExecution("OUTDOOR_TEMPERATURE")){
            while (rs.next()) {
                OutDoorTemperature outDoorTemperatureDTO = new OutDoorTemperature(
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
