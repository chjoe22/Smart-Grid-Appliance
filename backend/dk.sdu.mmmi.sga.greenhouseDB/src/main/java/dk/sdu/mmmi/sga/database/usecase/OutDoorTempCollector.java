package dk.sdu.mmmi.sga.database.usecase;

import dk.sdu.mmmi.sga.database.entity.OutDoorTemperature;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.db.util.DBConn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OutDoorTempCollector extends DBConn implements DataCollection<OutDoorTemperature> {

    @Override
    public String getName() {
        return "OutDoor Temperature";
    }

    @Override
    public List<OutDoorTemperature> collect() {
        return incrementQueryExecution("OUTDOOR_TEMPERATURE", 100, rs -> {
            List<OutDoorTemperature> results = new ArrayList<>();
            while (rs.next()) {
                OutDoorTemperature outDoorTemperature = new OutDoorTemperature(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("CELCIUS")
                );
                results.add(outDoorTemperature);
            }
            return results;
        });
    }
}
