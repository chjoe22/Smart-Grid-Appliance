package dk.sdu.mmmi.sga.auth.config;

import dk.sdu.mmmi.sga.auth.entity.Roles;
import dk.sdu.mmmi.sga.auth.entity.User;
import dk.sdu.mmmi.sga.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class StartUserDataConfig {
    @Bean
    public CommandLineRunner initAdminAccount(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            userRepository.deleteAll();
            User admin = new User();
            admin.setEmail("admin@admin.com");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRole(Roles.ADMIN);
            userRepository.save(admin);
        };
    }
}
