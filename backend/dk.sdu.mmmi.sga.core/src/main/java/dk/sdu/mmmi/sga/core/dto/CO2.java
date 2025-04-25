package dk.sdu.mmmi.sga.core.dto;


import java.sql.Timestamp;



public record CO2 (int id, int context_id, Timestamp timestamp, double ppm) {}
