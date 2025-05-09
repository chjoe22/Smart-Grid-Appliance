package dk.sdu.mmmi.sga.auth.controller;

import dk.sdu.mmmi.sga.auth.entity.User;
import dk.sdu.mmmi.sga.auth.repository.UserRepository;
import dk.sdu.mmmi.sga.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        Optional<User> userOptional = userService.findByUsername(loginRequest.getUsername());

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).body("User not found");
        }

        User user = userOptional.get();

        boolean passwordMatches = new BCryptPasswordEncoder().matches(
                loginRequest.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        return ResponseEntity.ok("Login successful");
    }



    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}

