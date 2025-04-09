package sdu.mmmi.sga.spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdu.mmmi.sga.spring.service.DataCollectorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataCollectorController {
    private final DataCollectorService dataCollectorService;

    public DataCollectorController(DataCollectorService dataCollectorService) {
        this.dataCollectorService = dataCollectorService;
    }

    @GetMapping
    public Map<String, List<?>> getDataCollection() {
        return dataCollectorService.getDataCollection();
    }

    @GetMapping("/{collectorName}")
    public List<?> getDataCollection(@PathVariable String collectorName) {
        return dataCollectorService.getDataCollection(collectorName);
    }
}
