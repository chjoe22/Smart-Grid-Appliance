package dk.sdu.mmmi.sga.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SPAController {

    @GetMapping(value = "/{path:[^\\.]*}")
    public String forward() {
        return "forward:/";
    }
}
