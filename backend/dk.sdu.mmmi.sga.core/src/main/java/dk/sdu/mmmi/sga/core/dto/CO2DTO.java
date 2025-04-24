package dk.sdu.mmmi.sga.core.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CO2DTO {
    private int id;
    private int context_id;
    private Timestamp timestamp;
    private double ppm;

    public CO2DTO(int id, int context_id, Timestamp timestamp, double ppm) {
        this.id = id;
        this.context_id = context_id;
        this.timestamp = timestamp;
        this.ppm = ppm;
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

    public double getPpm() {
        return ppm;
    }
}
