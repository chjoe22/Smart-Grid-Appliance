package dk.sdu.mmmi.sga.database.controller;

import dk.sdu.mmmi.sga.database.dto.AirTemperatureResponse;
import dk.sdu.mmmi.sga.database.mapper.AirTemperatureMapper;
import dk.sdu.mmmi.sga.database.usecase.AirTempCollector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data/airtemperature")
public class AirTemperatureController {

    private final AirTempCollector airTempCollector;
    private final AirTemperatureMapper airTemperatureMapper;

    public AirTemperatureController(AirTempCollector airTempCollector, AirTemperatureMapper airTemperatureMapper) {
        this.airTempCollector = airTempCollector;
        this.airTemperatureMapper = airTemperatureMapper;
    }

    @GetMapping
    @Scheduled(fixedRate = 10000)
    public List<AirTemperatureResponse> getAirTemperatures() {
        return airTempCollector.collect().stream()
                .map(airTemperatureMapper::toResponse)
                .toList();
    }
    @GetMapping("/{id}")
    public AirTemperatureResponse getAirTemperatureById(@PathVariable int id) {
        return airTempCollector.collect().stream()
                .filter(airTemperature -> airTemperature.getId() == id)
                .map(airTemperatureMapper::toResponse)
                .findFirst()
                .orElse(null);
    }
}
