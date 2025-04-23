package dk.sdu.mmmi.sga.database.models;

import java.sql.Timestamp;

public class Humidity {
    private int id;
    private int context_id;
    private Timestamp timestamp;
    private double humidity;

    public Humidity(int id, int context_id, Timestamp time, double humidity) {
        this.id = id;
        this.context_id = context_id;
        this.timestamp = time;
        this.humidity = humidity;
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
    public double getHumidity() {
        return humidity;
    }
    @Override
    public String toString() {
        return "Humidity{" +
                "id=" + id +
                ", context_id=" + context_id +
                ", timestamp=" + timestamp +
                ", humidity=" + humidity +
                '}';
    }
}
