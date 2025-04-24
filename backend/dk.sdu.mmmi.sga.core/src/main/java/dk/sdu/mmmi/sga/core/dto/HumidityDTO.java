package dk.sdu.mmmi.sga.core.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class HumidityDTO {
    private int id;
    private int context_id;
    private Timestamp timestamp;
    private double humidity;

    public HumidityDTO(int id, int context_id, Timestamp timestamp, double humidity) {
        this.id = id;
        this.context_id = context_id;
        this.timestamp = timestamp;
        this.humidity = humidity;
    }

    public int getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getContext_id() {
        return context_id;
    }

    public double getHumidity() {
        return humidity;
    }
}
