package dk.sdu.mmmi.sga.database.entity;


import dk.sdu.mmmi.sga.core.entity.Sensor;
import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
public class AirTemperature extends Sensor {
    private double celcius;

    public AirTemperature(int id, int contextId, Timestamp timestamp, double celcius) {
        super(id, contextId, timestamp);
        this.celcius = celcius;
    }

    public AirTemperature() {}

}

