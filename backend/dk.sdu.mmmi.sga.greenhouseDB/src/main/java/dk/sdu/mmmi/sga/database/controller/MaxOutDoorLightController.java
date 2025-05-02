package dk.sdu.mmmi.sga.database.controller;

import dk.sdu.mmmi.sga.database.dto.MaxOutDoorLightResponse;
import dk.sdu.mmmi.sga.database.mapper.MaxOutDoorLightMapper;
import dk.sdu.mmmi.sga.database.usecase.MaxOutDoorLightCollector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data/maxoutdoorlight")
public class MaxOutDoorLightController {

    private final MaxOutDoorLightCollector maxOutDoorLightCollector;
    private final MaxOutDoorLightMapper maxOutDoorLightMapper;

    public MaxOutDoorLightController(MaxOutDoorLightCollector maxOutDoorLightCollector, MaxOutDoorLightMapper maxOutDoorLightMapper) {
        this.maxOutDoorLightCollector = maxOutDoorLightCollector;
        this.maxOutDoorLightMapper = maxOutDoorLightMapper;
    }

    @GetMapping
    @Scheduled(fixedRate = 10000)
    public List<MaxOutDoorLightResponse> getMaxOutDoorLight() {
        return maxOutDoorLightCollector.collect().stream()
                .map(maxOutDoorLightMapper::toResponse)
                .toList();
    }
    @GetMapping("/{id}")
    public MaxOutDoorLightResponse getMaxOutDoorLightById(@PathVariable int id) {
        return maxOutDoorLightCollector.collect().stream()
                .filter(maxOutDoorLight -> maxOutDoorLight.id() == id)
                .map(maxOutDoorLightMapper::toResponse)
                .findFirst()
                .orElse(null);
    }
}
