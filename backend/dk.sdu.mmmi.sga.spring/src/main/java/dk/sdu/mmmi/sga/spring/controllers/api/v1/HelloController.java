package dk.sdu.mmmi.sga.spring.controllers.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dk.sdu.mmmi.sga.spring.data.User;
import dk.sdu.mmmi.sga.spring.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    // Test controller to simulate a service that returns a list of users
    private UserService userService;

    public HelloController() {
        this.userService = new UserService();
    }
    // Test controller to simulate a service that returns a list of users
    @RequestMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
