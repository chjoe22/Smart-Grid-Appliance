package dk.sdu.mmmi.sga.database.usecase;

import dk.sdu.mmmi.sga.database.entity.CO2;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.db.util.DBConn;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CO2Collector extends DBConn implements DataCollection<CO2> {

    @Override
    public String getName() {
        return "CO2";
    }

    @SneakyThrows
    @Override
    public List<CO2> collect() {
        return incrementQueryExecution("CO2", 100, rs -> {
            List<CO2> results = new ArrayList<>();
            while (rs.next()){
                CO2 co2 = new CO2(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("PPM")
                );
                results.add(co2);
            }
            return results;
        });
    }
}
