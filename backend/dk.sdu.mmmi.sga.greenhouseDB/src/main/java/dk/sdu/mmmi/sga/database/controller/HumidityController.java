package dk.sdu.mmmi.sga.database.controller;

import dk.sdu.mmmi.sga.database.dto.HumidityResponse;
import dk.sdu.mmmi.sga.database.mapper.HumidityMapper;
import dk.sdu.mmmi.sga.database.usecase.HumidityCollector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data/humidity")
public class HumidityController {

    private final HumidityCollector humidityCollector;
    private final HumidityMapper humidityMapper;

    public HumidityController(HumidityCollector humidityCollector, HumidityMapper humidityMapper) {
        this.humidityCollector = humidityCollector;
        this.humidityMapper = humidityMapper;
    }

    @GetMapping
    public List<HumidityResponse> getHumidity() {
        return humidityCollector.collect().stream()
                .map(humidityMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public HumidityResponse getHumidityById(@PathVariable int id) {
        return humidityCollector.collect().stream()
                .filter(humidity -> humidity.id() == id)
                .map(humidityMapper::toResponse)
                .findFirst()
                .orElse(null);
    }
}
