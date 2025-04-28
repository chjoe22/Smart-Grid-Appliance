package dk.sdu.mmmi.sga.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HumidityResponse {
    private int id;
    private int context_id;
    private Timestamp timestamp;
    private double humidity;
}
