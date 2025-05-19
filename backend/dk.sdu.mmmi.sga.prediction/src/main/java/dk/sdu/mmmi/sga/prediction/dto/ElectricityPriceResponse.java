package dk.sdu.mmmi.sga.prediction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectricityPriceResponse {
    private LocalDateTime time;
    private double predictionPrice;
}
