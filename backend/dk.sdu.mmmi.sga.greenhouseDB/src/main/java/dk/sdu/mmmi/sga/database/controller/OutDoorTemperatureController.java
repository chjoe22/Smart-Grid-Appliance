package dk.sdu.mmmi.sga.database.controller;

import dk.sdu.mmmi.sga.database.dto.OutDoorTemperatureResponse;
import dk.sdu.mmmi.sga.database.mapper.OutDoorTemperatureMapper;
import dk.sdu.mmmi.sga.database.usecase.OutDoorTempCollector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data/outdoortemperature")
public class OutDoorTemperatureController {

    private final OutDoorTempCollector outDoorTempCollector;
    private final OutDoorTemperatureMapper outDoorTemperatureMapper;

    public OutDoorTemperatureController(OutDoorTempCollector outDoorTempCollector, OutDoorTemperatureMapper outDoorTemperatureMapper) {
        this.outDoorTempCollector = outDoorTempCollector;
        this.outDoorTemperatureMapper = outDoorTemperatureMapper;
    }
    @GetMapping
    @Scheduled(fixedRate = 10000)
    public List<OutDoorTemperatureResponse> getOutDoorTemperatures() {
        return outDoorTempCollector.collect().stream()
                .map(outDoorTemperatureMapper::toResponse)
                .toList();
    }
    @GetMapping("/{id}")
    public OutDoorTemperatureResponse getOutDoorTemperatureById(@PathVariable int id) {
        return outDoorTempCollector.collect().stream()
                .filter(outDoorTemperature -> outDoorTemperature.getId() == id)
                .map(outDoorTemperatureMapper::toResponse)
                .findFirst()
                .orElse(null);
    }
}
