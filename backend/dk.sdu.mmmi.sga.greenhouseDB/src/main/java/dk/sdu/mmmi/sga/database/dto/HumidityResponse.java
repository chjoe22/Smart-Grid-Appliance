package dk.sdu.mmmi.sga.database.dto;


import java.sql.Timestamp;

public record HumidityResponse (int id, int context_id, Timestamp timestamp, double humidity) {}
