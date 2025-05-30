package dk.sdu.mmmi.sga.database.dto;


import java.sql.Timestamp;



public record CO2Response (int id, int context_id, Timestamp timestamp, double ppm) {}
