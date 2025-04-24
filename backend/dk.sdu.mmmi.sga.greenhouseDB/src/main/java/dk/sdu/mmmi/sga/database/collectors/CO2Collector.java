package dk.sdu.mmmi.sga.database.collectors;

import dk.sdu.mmmi.sga.core.dto.CO2DTO;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CO2Collector extends DatabaseConnection implements DataCollection<CO2DTO> {

    public CO2Collector(){
        super();
    }
    @Override
    public String getName() {
        return "CO2";
    }

    @Override
    public List<CO2DTO> collect() {
        List<CO2DTO> results = new ArrayList<>();
        try (ResultSet rs = queryExecution("CO2")){
            while (rs.next()){
                CO2DTO co2DTO = new CO2DTO(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("PPM")
                );
                results.add(co2DTO);
            }
        }catch (SQLException sqlE){
            sqlE.printStackTrace();
        }
        return results;
    }
}
