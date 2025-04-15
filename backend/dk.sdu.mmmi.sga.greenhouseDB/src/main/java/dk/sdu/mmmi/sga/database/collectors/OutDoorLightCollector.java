package dk.sdu.mmmi.sga.database.collectors;

import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.database.reader.DatabaseConnection;
import dk.sdu.mmmi.sga.database.reader.GreenhouseDBReader;

import java.util.List;
import java.util.Map;

public class OutDoorLightCollector extends DatabaseConnection implements DataCollection<Map<String, Object>> {
    @Override
    public String getName() {
        return "OutDoorLight";
    }

    @Override
    public List<Map<String, Object>> collect() {
        GreenhouseDBReader reader = new GreenhouseDBReader();
        return reader.fetchData("OUTDOOR_LIGHT");
    }
}
