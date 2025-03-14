package sdu.mmmi.sga.spring.controllers.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ByeController {
    @RequestMapping("/bye")
    public String ByeController(){
        return "{\"message\": \"Bye, World!\"}";
    }
}
