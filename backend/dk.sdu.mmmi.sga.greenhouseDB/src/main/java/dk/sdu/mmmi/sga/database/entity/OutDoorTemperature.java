package dk.sdu.mmmi.sga.database.entity;

import dk.sdu.mmmi.sga.core.entity.Sensor;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
public class OutDoorTemperature extends Sensor {
    private double celsius;

    public OutDoorTemperature(int id, int contextId, Timestamp timestamp, double celsius) {
        super(id, contextId, timestamp);
        this.celsius = celsius;
    }

    public OutDoorTemperature() {}
}
