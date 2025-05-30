package dk.sdu.mmmi.sga.core.entity;

import java.sql.Timestamp;


public abstract class Sensor {
    private int id;
    private int contextId;
    private Timestamp timestamp;

    public Sensor(int id, int contextId, Timestamp timestamp) {
        this.id = id;
        this.contextId = contextId;
        this.timestamp = timestamp;
    }

    public Sensor() {}

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setContextId(int contextId) {
        this.contextId = contextId;
    }

    public int getContextId() {
        return contextId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
