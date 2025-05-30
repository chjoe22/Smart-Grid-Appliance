package dk.sdu.mmmi.sga.web.controllers;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class HealthController {

    private final MeterRegistry meterRegistry;
    private final HealthEndpoint healthEndpoint;

    public HealthController(MeterRegistry meterRegistry, HealthEndpoint healthEndpoint) {
        this.meterRegistry = meterRegistry;
        this.healthEndpoint = healthEndpoint;
    }

    @GetMapping("/metric/uptime")
    public Map<String, Object> health(){
        Double uptime = Objects.requireNonNull(meterRegistry.find("process.uptime").gauge()).value();
        return Map.of("process.uptime", uptime);
    }

    @GetMapping("/metric/health")
    public HealthComponent getHealth() {

        return healthEndpoint.health();
    }
}
