package com.talkode.project.config;

import com.talkode.project.entities.CustomUser;
import com.talkode.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public DBInitializer() {}

    @Override
    public void run(String... args) throws Exception {
        CustomUser user = new CustomUser(
                "admin",
                "admin",
                new BCryptPasswordEncoder().encode("rKqOtuMNE24Cr4Qi")
        );

        this.userRepository.save(user);
    }
}
