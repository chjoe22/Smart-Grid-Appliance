package dk.sdu.mmmi.sga.auth.controller;

import dk.sdu.mmmi.sga.auth.entity.User;
import dk.sdu.mmmi.sga.auth.repository.UserRepository;
import dk.sdu.mmmi.sga.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        User user = userService.findByUsername(loginUser.getUsername());

        if (user == null) {
            return ResponseEntity.status(401).body("User not found");
        }

        if (!user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.status(401).body("Invalid password");
        }

        return ResponseEntity.ok("Login successful");
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
