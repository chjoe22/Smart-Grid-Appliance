package dk.sdu.mmmi.sga.database.usecase;

import dk.sdu.mmmi.sga.database.entity.MaxOutDoorLight;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.db.util.DBConn;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class MaxOutDoorLightCollector extends DBConn implements DataCollection<MaxOutDoorLight> {
    @Override
    public String getName() {
        return "Max OutDoor Light";
    }

    /**
     * Collects maximum outdoor light data from the database.
     *
     * @return a list of MaxOutDoorLight objects containing the collected data
     */
    @Override
    public List<MaxOutDoorLight> collect() {
        return incrementQueryExecution("MAX_OUTDOOR_LIGHT", 100, rs -> {
            List<MaxOutDoorLight> results = new ArrayList<>();
            while (rs.next()) {
                MaxOutDoorLight maxOutDoorLight = new MaxOutDoorLight(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("WATTM2")
                );
                results.add(maxOutDoorLight);
            }
            return results;
        });
    }
}
