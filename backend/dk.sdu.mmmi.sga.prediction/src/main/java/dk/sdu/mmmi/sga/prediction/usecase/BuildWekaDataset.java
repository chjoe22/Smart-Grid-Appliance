package dk.sdu.mmmi.sga.prediction.usecase;

import dk.sdu.mmmi.sga.prediction.entity.ElectricityPrice;
import org.springframework.stereotype.Component;
import weka.core.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BuildWekaDataset {
    public Instances from(List<ElectricityPrice> prices){
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("hour"));
        attributes.add(new Attribute("dayOfWeek"));
        attributes.add(new Attribute("month"));
        attributes.add(new Attribute("year"));
        attributes.add(new Attribute("euro"));

        Instances dataset = new Instances("ElectricityPrice", attributes, prices.size());
        dataset.setClassIndex(4);

        for (ElectricityPrice price : prices) {
            LocalDateTime date = price.timestamp();
            double hour = date.getHour();
            double dayOfWeek = date.getDayOfWeek().getValue();
            double month = date.getMonthValue();
            double year = date.getYear();
            double euro = price.price();

            double[] values = new double[]{hour, dayOfWeek, month, year, euro};

            dataset.add(new DenseInstance(1.0, values));
        }
        return dataset;
    }
}
