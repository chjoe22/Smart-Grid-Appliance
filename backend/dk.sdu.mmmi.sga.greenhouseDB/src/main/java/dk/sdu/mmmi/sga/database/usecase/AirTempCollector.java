package dk.sdu.mmmi.sga.database.usecase;

import dk.sdu.mmmi.sga.database.entity.AirTemperature;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.db.util.DBConn;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AirTempCollector extends DBConn implements DataCollection<AirTemperature> {


    @Override
    public String getName() {
        return "Air Temperature";
    }

    /**
     * Collects air temperature data from the database.
     *
     * @return a list of AirTemperature objects containing the collected data
     */
    @SneakyThrows
    @Override
    public List<AirTemperature> collect() {
        return incrementQueryExecution("AIR_TEMPERATURE", 250, rs -> {
            List<AirTemperature> results = new ArrayList<>();
            while (rs.next()) {
                AirTemperature airTemp = new AirTemperature(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("CELCIUS")
                );
                results.add(airTemp);
            }
            return results;
        });
    }
}
