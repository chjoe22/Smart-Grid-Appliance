package dk.sdu.mmmi.sga.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {
        "dk.sdu.mmmi.sga.core",
        "dk.sdu.mmmi.sga.web",
        "dk.sdu.mmmi.sga.database",
        "dk.sdu.mmmi.sga.auth"
})
@EnableJpaRepositories(basePackages = "dk.sdu.mmmi.sga.auth.repository")
public class Application {

    // use ./mvnw spring-boot:run to run the spring application
    // can also use mvn spring-boot:run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
