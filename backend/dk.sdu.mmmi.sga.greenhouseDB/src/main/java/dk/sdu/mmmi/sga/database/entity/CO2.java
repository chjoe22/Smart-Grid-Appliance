package dk.sdu.mmmi.sga.database.entity;

import dk.sdu.mmmi.sga.core.entity.Sensor;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
public class CO2 extends Sensor {
    private double ppm;

    public CO2(int id, int contextId, Timestamp timestamp, double ppm) {
        super(id, contextId, timestamp);
        this.ppm = ppm;
    }

    public CO2() {}

}
