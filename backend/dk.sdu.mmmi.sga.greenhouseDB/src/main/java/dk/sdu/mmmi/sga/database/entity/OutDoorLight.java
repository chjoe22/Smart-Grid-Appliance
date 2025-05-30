package dk.sdu.mmmi.sga.database.entity;

import dk.sdu.mmmi.sga.core.entity.Sensor;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
public class OutDoorLight extends Sensor {
    private double wattm2;

    public OutDoorLight(int id, int contextId, Timestamp timestamp, double wattm2) {
        super(id, contextId, timestamp);
        this.wattm2 = wattm2;
    }

    public OutDoorLight() {}
}
