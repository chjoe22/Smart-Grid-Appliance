package dk.sdu.mmmi.sga.database.entity;


import java.sql.Timestamp;



public record CO2 (int id, int context_id, Timestamp timestamp, double ppm) {}
