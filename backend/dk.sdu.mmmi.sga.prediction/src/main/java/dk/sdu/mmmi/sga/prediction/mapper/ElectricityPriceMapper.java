package dk.sdu.mmmi.sga.prediction.mapper;

import dk.sdu.mmmi.sga.prediction.dto.ElectricityPriceResponse;
import dk.sdu.mmmi.sga.prediction.entity.ElectricityPrice;
import org.springframework.stereotype.Component;

@Component
public class ElectricityPriceMapper {
     public ElectricityPriceResponse toResponse(ElectricityPrice entity) {
         return new ElectricityPriceResponse(
                 entity.timestamp(),
                 entity.price()
         );
     }
}
