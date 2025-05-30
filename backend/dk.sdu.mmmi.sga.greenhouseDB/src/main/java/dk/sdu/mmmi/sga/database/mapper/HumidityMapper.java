package dk.sdu.mmmi.sga.database.mapper;

import dk.sdu.mmmi.sga.database.dto.HumidityResponse;
import dk.sdu.mmmi.sga.database.entity.Humidity;
import org.springframework.stereotype.Component;

@Component
public class HumidityMapper {
    public HumidityResponse toResponse(Humidity entity) {
        return new HumidityResponse(
                entity.getId(),
                entity.getContextId(),
                entity.getTimestamp(),
                entity.getHumidity()
        );
    }
}
