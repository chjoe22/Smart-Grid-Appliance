package dk.sdu.mmmi.sga.database.collectors;

import dk.sdu.mmmi.sga.core.dto.CO2;
import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CO2Collector extends DatabaseConnection implements DataCollection<CO2> {

    public CO2Collector(){
        super();
    }
    @Override
    public String getName() {
        return "CO2";
    }

    @Override
    public List<CO2> collect() {
        List<CO2> results = new ArrayList<>();
        try (ResultSet rs = queryExecution("CO2")){
            while (rs.next()){
                CO2 co2DTO = new CO2(
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
