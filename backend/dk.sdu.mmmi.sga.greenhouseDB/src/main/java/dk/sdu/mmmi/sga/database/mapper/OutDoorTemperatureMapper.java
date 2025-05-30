package dk.sdu.mmmi.sga.database.mapper;

import dk.sdu.mmmi.sga.database.dto.OutDoorTemperatureResponse;
import dk.sdu.mmmi.sga.database.entity.OutDoorTemperature;
import org.springframework.stereotype.Component;

@Component
public class OutDoorTemperatureMapper {
    public OutDoorTemperatureResponse toResponse(OutDoorTemperature entity) {
        return new OutDoorTemperatureResponse(
                entity.getId(),
                entity.getContextId(),
                entity.getTimestamp(),
                entity.getCelsius()
        );
    }
}
