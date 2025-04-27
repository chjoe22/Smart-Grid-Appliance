package dk.sdu.mmmi.sga.database.entity;


import java.sql.Timestamp;

public record OutDoorTemperature (int id, int context_id, Timestamp timestamp, double celcius) {}

