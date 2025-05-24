package dk.sdu.mmmi.sga.prediction.controller;

import dk.sdu.mmmi.sga.prediction.dto.ElectricityPriceResponse;
import dk.sdu.mmmi.sga.prediction.usecase.PredictElectricityPrice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prediction/el")
public class ElectricityPriceController {
    private final PredictElectricityPrice predictElectricityPrice;

    public ElectricityPriceController(PredictElectricityPrice predictElectricityPrice) {
        this.predictElectricityPrice = predictElectricityPrice;
    }

    @GetMapping("/next")
    public ResponseEntity<List<ElectricityPriceResponse>> getPredictedPrices(@RequestParam(defaultValue = "24") int steps) {
        try {
            System.out.println("Predicting next " + steps + " hours of electricity prices");
            List<ElectricityPriceResponse> predictions = predictElectricityPrice.predictNextHours(steps);
            System.out.println("Predicted prices: " + predictions);
            return ResponseEntity.ok(predictions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
