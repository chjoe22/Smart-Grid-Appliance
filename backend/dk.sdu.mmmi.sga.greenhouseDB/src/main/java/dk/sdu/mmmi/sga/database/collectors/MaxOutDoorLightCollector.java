package dk.sdu.mmmi.sga.database.collectors;

import dk.sdu.mmmi.sga.core.dto.MaxOutDoorLightDTO;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MaxOutDoorLightCollector extends DatabaseConnection implements DataCollection<MaxOutDoorLightDTO> {

    public MaxOutDoorLightCollector() {
        super();
    }

    @Override
    public String getName() {
        return "Max OutDoor Light";
    }

    @Override
    public List<MaxOutDoorLightDTO> collect() {
        List<MaxOutDoorLightDTO> results = new ArrayList<>();
        try (ResultSet rs = queryExecution("MAX_OUTDOOR_LIGHT")) {
            while (rs.next()) {
                MaxOutDoorLightDTO maxOutDoorLightDTO = new MaxOutDoorLightDTO(
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
