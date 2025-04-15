package dk.sdu.mmmi.sga.database.collectors;

import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.models.Humidity;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HumidityCollector extends DatabaseConnection implements DataCollection<Humidity> {

    private int i = 1;
    public HumidityCollector() {
        super();
    }

    @Override
    public String getName() {
        return "Humidity";
    }

    @Override
    public List<Humidity> collect() {
        List<Humidity> results = new ArrayList<>();
        try(ResultSet rs = queryExecution("SELECT * FROM APP.HUMIDITY ORDER BY TIME ASC FETCH FIRST "+i+" ROWS ONLY")) {
            while (rs.next()){
                results.add(new Humidity(
                        rs.getInt("ID"),
                        rs.getInt("CONTEXT_ID"),
                        rs.getTimestamp("TIME"),
                        rs.getDouble("FACTOR")
                ));
            }
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        }
        i++;
        if (i > 100) i--;
        return results;
    }

    @Override
    public void close() {
    }
}
