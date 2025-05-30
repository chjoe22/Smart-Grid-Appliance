package dk.sdu.mmmi.sga.database.mapper;

import dk.sdu.mmmi.sga.database.dto.CO2Response;
import dk.sdu.mmmi.sga.database.entity.CO2;
import org.springframework.stereotype.Component;

@Component
public class CO2Mapper {

    public CO2Response toResponse(CO2 entity) {
        return new CO2Response(
                entity.getId(),
                entity.getContextId(),
                entity.getTimestamp(),
                entity.getPpm()
        );
    }
}
