package dk.sdu.mmmi.sga.database.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = "dk.sdu.mmmi.sga.database")
public class GreenhouseDBConfig {
}
