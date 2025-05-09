package dk.sdu.mmmi.sga.auth.service;

import dk.sdu.mmmi.sga.auth.entity.User;
import dk.sdu.mmmi.sga.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean login(String username, String password) {
        return userRepository.findByUsername(username)
                .map(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElse(false);
    }


}
