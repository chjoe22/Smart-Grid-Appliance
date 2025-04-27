package dk.sdu.mmmi.sga.database.entity;


import java.sql.Timestamp;

public record OutDoorLight (int id, int context_id, Timestamp timestamp, double wattm2) {}
