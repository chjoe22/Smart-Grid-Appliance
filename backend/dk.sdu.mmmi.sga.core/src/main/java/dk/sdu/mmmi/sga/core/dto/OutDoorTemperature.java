package dk.sdu.mmmi.sga.core.dto;


import java.sql.Timestamp;

public record OutDoorTemperature (int id, int context_id, Timestamp timestamp, double celcius) {}

