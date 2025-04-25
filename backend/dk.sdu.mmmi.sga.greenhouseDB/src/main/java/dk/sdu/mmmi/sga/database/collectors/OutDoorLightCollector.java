package dk.sdu.mmmi.sga.database.collectors;

import dk.sdu.mmmi.sga.core.dto.OutDoorLight;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OutDoorLightCollector extends DatabaseConnection implements DataCollection<OutDoorLight> {

    public OutDoorLightCollector() {
        super();
    }

    @Override
    public String getName() {
        return "OutDoorLight";
    }

    @Override
    public List<OutDoorLight> collect() {
        List<OutDoorLight> results = new ArrayList<>();
        try (ResultSet rs = queryExecution("OUTDOOR_LIGHT")){
            while(rs.next()){
                OutDoorLight outDoorLightDTO = new OutDoorLight(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("WATTM2")
                );
                results.add(outDoorLightDTO);
            }
        }catch (SQLException sqlE){
            sqlE.printStackTrace();
        }
        return results;
    }
}
