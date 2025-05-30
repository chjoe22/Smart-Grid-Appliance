package dk.sdu.mmmi.sga.database.mapper;

import dk.sdu.mmmi.sga.database.dto.AirTemperatureResponse;
import dk.sdu.mmmi.sga.database.entity.AirTemperature;
import org.springframework.stereotype.Component;

@Component
public class AirTemperatureMapper {

    public AirTemperatureResponse toResponse(AirTemperature entity) {
        return new AirTemperatureResponse(
                entity.getId(),
                entity.getContextId(),
                entity.getTimestamp(),
                entity.getCelcius()
        );
    }
}
