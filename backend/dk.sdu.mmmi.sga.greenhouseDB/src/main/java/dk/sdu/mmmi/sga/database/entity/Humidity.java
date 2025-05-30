package dk.sdu.mmmi.sga.database.entity;

import dk.sdu.mmmi.sga.core.entity.Sensor;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
public class Humidity extends Sensor {

    private double humidity;

    public Humidity(int id, int contextId, Timestamp timestamp, double humidity) {
        super(id, contextId, timestamp);
        this.humidity = humidity;
    }

    public Humidity(){}


}
