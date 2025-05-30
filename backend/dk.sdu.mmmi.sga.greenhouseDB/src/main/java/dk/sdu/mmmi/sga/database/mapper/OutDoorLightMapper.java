package dk.sdu.mmmi.sga.database.mapper;

import dk.sdu.mmmi.sga.database.dto.OutDoorLightResponse;
import dk.sdu.mmmi.sga.database.entity.OutDoorLight;
import org.springframework.stereotype.Component;

@Component
public class OutDoorLightMapper {
    public OutDoorLightResponse toResponse(OutDoorLight entity) {
        return new OutDoorLightResponse(
                entity.getId(),
                entity.getContextId(),
                entity.getTimestamp(),
                entity.getWattm2()
        );
    }
}
