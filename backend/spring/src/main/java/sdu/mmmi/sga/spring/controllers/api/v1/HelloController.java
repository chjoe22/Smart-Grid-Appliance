package sdu.mmmi.sga.spring.controllers.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "{\"message\": \"Hello, World!\"}";
    }
}
