package dk.sdu.mmmi.sga.database.controller;

import dk.sdu.mmmi.sga.database.dto.OutDoorLightResponse;
import dk.sdu.mmmi.sga.database.mapper.OutDoorLightMapper;
import dk.sdu.mmmi.sga.database.usecase.OutDoorLightCollector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data/outdoorlight")
public class OutDoorLightController {

    private final OutDoorLightCollector outDoorLightCollector;
    private final OutDoorLightMapper outDoorLightMapper;

    public OutDoorLightController(OutDoorLightCollector outDoorLightCollector, OutDoorLightMapper outDoorLightMapper) {
        this.outDoorLightCollector = outDoorLightCollector;
        this.outDoorLightMapper = outDoorLightMapper;
    }

    @GetMapping
    @Scheduled(fixedRate = 10000)
    public List<OutDoorLightResponse> getOutDoorLight() {
        return outDoorLightCollector.collect().stream()
                .map(outDoorLightMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public OutDoorLightResponse getOutDoorLightById(@PathVariable int id) {
        return outDoorLightCollector.collect().stream()
                .filter(outDoorLight -> outDoorLight.getId() == id)
                .map(outDoorLightMapper::toResponse)
                .findFirst()
                .orElse(null);
    }
}
