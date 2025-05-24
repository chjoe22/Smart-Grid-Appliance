package dk.sdu.mmmi.sga.database.usecase;

import dk.sdu.mmmi.sga.database.entity.OutDoorLight;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.db.util.DBConn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OutDoorLightCollector extends DBConn implements DataCollection<OutDoorLight> {

    @Override
    public String getName() {
        return "OutDoor Light";
    }

    /**
     * Collects outdoor light data from the database.
     *
     * @return a list of OutDoorLight objects containing the collected data
     */
    @Override
    public List<OutDoorLight> collect() {
        return incrementQueryExecution("OUTDOOR_LIGHT", 250, rs -> {
            List<OutDoorLight> results = new ArrayList<>();
            while(rs.next()){
                OutDoorLight outDoorLight = new OutDoorLight(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("WATTM2")
                );
                results.add(outDoorLight);
            }
            return results;
        });
    }
}
