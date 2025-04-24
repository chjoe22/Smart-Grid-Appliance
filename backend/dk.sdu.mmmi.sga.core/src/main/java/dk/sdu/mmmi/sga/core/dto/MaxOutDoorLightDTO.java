package dk.sdu.mmmi.sga.core.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MaxOutDoorLightDTO {
    private int id;
    private int context_id;
    private Timestamp timestamp;
    private double wattm2;

    public MaxOutDoorLightDTO(int id, int context_id, Timestamp timestamp, double wattm2) {
        this.id = id;
        this.context_id = context_id;
        this.timestamp = timestamp;
        this.wattm2 = wattm2;
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

    public double getWattm2() {
        return wattm2;
    }
}
