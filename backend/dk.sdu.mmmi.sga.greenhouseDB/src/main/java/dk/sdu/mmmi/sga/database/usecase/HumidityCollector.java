package dk.sdu.mmmi.sga.database.usecase;

import dk.sdu.mmmi.sga.database.entity.Humidity;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.db.util.DBConn;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HumidityCollector extends DBConn implements DataCollection<Humidity> {

    @Override
    public String getName() {
        return "Humidity";
    }


    /**
     * Collects humidity data from the database.
     *
     * @return a list of Humidity objects containing the collected data
     */
    @SneakyThrows
    @Override
    public List<Humidity> collect() {
        return incrementQueryExecution("HUMIDITY", 250, rs  -> {
            List<Humidity> results = new ArrayList<>();
            while (rs.next()) {
                Humidity humidity = new Humidity(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("FACTOR")
                );
                results.add(humidity);
            }
            return results;
        });
    }
}
