package sdu.mmmi.sga.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // use ./mvnw spring-boot:run to run the spring application
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
