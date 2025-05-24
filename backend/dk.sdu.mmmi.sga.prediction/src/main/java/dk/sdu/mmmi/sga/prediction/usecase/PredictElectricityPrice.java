package dk.sdu.mmmi.sga.prediction.usecase;

import dk.sdu.mmmi.sga.prediction.dto.ElectricityPriceResponse;
import dk.sdu.mmmi.sga.prediction.entity.ElectricityPrice;
import org.springframework.stereotype.Component;
import weka.classifiers.functions.LinearRegression;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PredictElectricityPrice {
    private final ElectricityPriceCollector collector;
    private final BuildWekaDataset wekaDataset;

    public PredictElectricityPrice(ElectricityPriceCollector collector, BuildWekaDataset wekaDataset) {
        this.collector = collector;
        this.wekaDataset = wekaDataset;
    }

    public double predict(int hour, int dayOfWeek, int month, int year) throws Exception{
        List<ElectricityPrice> historicalPrices  = collector.collect();
        Instances dataset = wekaDataset.from(historicalPrices);
        LinearRegression model = new LinearRegression();
        model.buildClassifier(dataset);

        double[] newData = new double[]{hour, dayOfWeek, month, year};
        Instance newInstance = new DenseInstance(1.0, newData);
        newInstance.setDataset(dataset);

        return model.classifyInstance(newInstance);
    }

    public List<ElectricityPriceResponse> predictNextHours(int hours) throws Exception {
        List<ElectricityPrice> history = collector.collect();
        Instances dataset = wekaDataset.from(history);

        LinearRegression model = new LinearRegression();
        model.buildClassifier(dataset);

        LocalDateTime lastTime = history.get(history.size() - 1).timestamp();
        List<ElectricityPriceResponse> predictions = new ArrayList<>();

        for (int i = 1; i <= hours; i++) {
            LocalDateTime future = lastTime.plusHours(i);

            double[] input = new double[]{
                    future.getHour(),
                    future.getDayOfWeek().getValue(),
                    future.getMonthValue(),
                    future.getYear()
            };

            Instance instance = new DenseInstance(1.0, input);
            instance.setDataset(dataset);

            double predictedValue = model.classifyInstance(instance);

            predictions.add(new ElectricityPriceResponse(future, predictedValue));
        }

        return predictions;
    }

}
