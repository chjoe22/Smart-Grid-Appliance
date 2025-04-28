package dk.sdu.mmmi.sga.database.controller;

import dk.sdu.mmmi.sga.database.dto.CO2Response;
import dk.sdu.mmmi.sga.database.mapper.CO2Mapper;
import dk.sdu.mmmi.sga.database.usecase.CO2Collector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data/co2")
public class CO2Controller {

    private final CO2Collector co2Collector;
    private final CO2Mapper co2Mapper;

    public CO2Controller(CO2Collector co2Collector, CO2Mapper co2Mapper) {
        this.co2Collector = co2Collector;
        this.co2Mapper = co2Mapper;
    }

    @GetMapping
    public List<CO2Response> getCO2() {
        return co2Collector.collect().stream()
                .map(co2Mapper::toResponse)
                .toList();
    }
    @GetMapping("/{id}")
    public CO2Response getCO2ById(@PathVariable int id) {
        return co2Collector.collect().stream()
                .filter(co2 -> co2.id() == id)
                .map(co2Mapper::toResponse)
                .findFirst()
                .orElse(null);
    }
}
