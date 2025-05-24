package dk.sdu.mmmi.sga.auth.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "dk.sdu.mmmi.sga.auth")
@EnableJpaRepositories(basePackages = "dk.sdu.mmmi.sga.auth.repository")
@EntityScan(basePackages = "dk.sdu.mmmi.sga.auth.entity")
public class AuthConfig {
}
