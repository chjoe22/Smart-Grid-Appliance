package sdu.mmmi.sga.spring.controllers.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdu.mmmi.sga.spring.data.TempData;

@RestController
@RequestMapping("api/v1")
public class TempController {

    private double[] temp = {1.1, 2.5, 3.2, 4.7, 5.9};
    private int[] time = {1, 2, 3, 4, 5};

    @RequestMapping("/temp")
    public TempData TempController(){
        return new TempData(temp, time);
    }
}
