package dk.sdu.mmmi.sga.prediction.usecase;

import dk.sdu.mmmi.sga.core.services.DataCollection;
import dk.sdu.mmmi.sga.db.util.DBConn;
import dk.sdu.mmmi.sga.prediction.entity.ElectricityPrice;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ElectricityPriceCollector extends DBConn {

    public List<ElectricityPrice> collect() {
        return timeQueryExecution("COMBINED_EL_PRICES", 250, rs -> {
            List<ElectricityPrice> results = new ArrayList<>();
            while (rs.next()) {
                ElectricityPrice electricityPrice = new ElectricityPrice(
                        rs.getTimestamp("TIME").toLocalDateTime(),
                        rs.getDouble("EURO")
                );
                results.add(electricityPrice);
            }
            return results;
        });
    }

}
