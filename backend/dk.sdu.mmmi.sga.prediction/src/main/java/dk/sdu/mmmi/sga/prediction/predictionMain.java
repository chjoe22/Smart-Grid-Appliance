package dk.sdu.mmmi.sga.prediction;

import dk.sdu.mmmi.sga.prediction.dto.ElectricityPriceResponse;
import dk.sdu.mmmi.sga.prediction.usecase.BuildWekaDataset;
import dk.sdu.mmmi.sga.prediction.usecase.ElectricityPriceCollector;
import dk.sdu.mmmi.sga.prediction.usecase.PredictElectricityPrice;

import java.util.List;

public class predictionMain {
    public static void main(String[] args) {
        try {
            // Manual wiring (instead of @Component injection)
            ElectricityPriceCollector collector = new ElectricityPriceCollector();
            BuildWekaDataset builder = new BuildWekaDataset();
            PredictElectricityPrice predictor = new PredictElectricityPrice(collector, builder);

            // Predict a specific case: 15:00, Friday, July 2016
            double prediction = predictor.predict(15, 5, 7, 2016);
            List<ElectricityPriceResponse> predictions = predictor.predictNextHours(50);

            System.out.println("Predicted electricity price: " + prediction + " euro");
            for (int i = 0; i < predictions.toArray().length; i++) {
                System.out.println("Predicted electricity price for " + predictions.get(i).getTimestamp() + ": " + predictions.get(i).getPredictionPrice() + " euro");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
