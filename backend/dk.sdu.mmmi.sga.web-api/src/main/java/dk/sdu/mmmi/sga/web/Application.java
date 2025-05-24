package dk.sdu.mmmi.sga.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "dk.sdu.mmmi.sga")
public class Application {

    // use ./mvnw spring-boot:run to run the spring application
    // can also use mvn spring-boot:run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
