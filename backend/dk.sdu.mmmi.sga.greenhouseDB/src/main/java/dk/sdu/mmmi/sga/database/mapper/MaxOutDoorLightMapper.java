package dk.sdu.mmmi.sga.database.mapper;

import dk.sdu.mmmi.sga.database.dto.MaxOutDoorLightResponse;
import dk.sdu.mmmi.sga.database.entity.MaxOutDoorLight;
import org.springframework.stereotype.Component;

@Component
public class MaxOutDoorLightMapper {

    public MaxOutDoorLightResponse toResponse(MaxOutDoorLight entity) {
        return new MaxOutDoorLightResponse(
                entity.getId(),
                entity.getContextId(),
                entity.getTimestamp(),
                entity.getWattm2()
        );
    }
}
