package com.project.demo.Config;

import com.project.demo.Entity.enums.Roles;
import com.project.demo.Entity.model.User;
import com.project.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@gmail.com").isPresent()) {
            return;
        }
        if(userRepository.findByEmail("admin").isEmpty()){
            User admin=new User();
            admin.setName("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Roles.ADMIN);
            userRepository.save(admin);

        }
    }
}
