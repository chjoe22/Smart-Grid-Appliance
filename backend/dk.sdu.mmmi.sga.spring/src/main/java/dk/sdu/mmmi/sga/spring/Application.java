package dk.sdu.mmmi.sga.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    // use ./mvnw spring-boot:run to run the spring application
    // can also use mvn spring-boot:run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
