package dk.sdu.mmmi.sga.core.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OutDoorTemperatureDTO {
    private int id;
    private int context_id;
    private Timestamp timestamp;
    private double celcius;

    public OutDoorTemperatureDTO(int id, int context_id, Timestamp timestamp, double celcius) {
        this.id = id;
        this.context_id = context_id;
        this.timestamp = timestamp;
        this.celcius = celcius;
    }

    public int getId() {
        return id;
    }

    public int getContext_id() {
        return context_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public double getCelcius() {
        return celcius;
    }
}
