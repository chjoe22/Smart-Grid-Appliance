package dk.sdu.mmmi.sga.database.model;

import java.sql.Timestamp;

public class AirTemperature {
    private int id;
    private int context_id;
    private Timestamp timestamp;
    private double celcius;

    public AirTemperature(int id, int context_id, Timestamp timestamp, double celcius) {
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

    @Override
    public String toString() {
        return "AirTemperature{" +
                "id=" + id +
                ", context_id=" + context_id +
                ", timestamp=" + timestamp +
                ", celcius=" + celcius +
                '}';
    }
}
