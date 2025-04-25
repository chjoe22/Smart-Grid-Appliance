package dk.sdu.mmmi.sga.database.collectors;

import dk.sdu.mmmi.sga.core.dto.MaxOutDoorLight;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class MaxOutDoorLightCollector extends DatabaseConnection implements DataCollection<MaxOutDoorLight> {

    public MaxOutDoorLightCollector() {
        super();
    }

    @Override
    public String getName() {
        return "Max OutDoor Light";
    }

    @Override
    public List<MaxOutDoorLight> collect() {
        List<MaxOutDoorLight> results = new ArrayList<>();
        try (ResultSet rs = queryExecution("MAX_OUTDOOR_LIGHT")) {
            while (rs.next()) {
                MaxOutDoorLight maxOutDoorLightDTO = new MaxOutDoorLight(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("WATTM2")
                );
                results.add(maxOutDoorLightDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
