package dk.sdu.mmmi.sga.prediction.entity;

import java.time.LocalDateTime;

public record ElectricityPrice(LocalDateTime timestamp, double price) {}
