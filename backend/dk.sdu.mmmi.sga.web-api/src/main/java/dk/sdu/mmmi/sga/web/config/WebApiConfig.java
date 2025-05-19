package dk.sdu.mmmi.sga.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = "dk.sdu.mmmi.sga.web")
public class WebApiConfig {
}
