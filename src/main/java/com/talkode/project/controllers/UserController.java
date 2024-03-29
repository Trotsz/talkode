package com.talkode.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.ArrayList;

import com.talkode.project.entities.CustomUser;
import com.talkode.project.exceptions.NameLengthException;
import com.talkode.project.repositories.UserRepository;

@RestController
public class UserController {
    @Autowired
    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody CustomUser user) {
        String name = user.getName();
        int name_len = name.length();

        char name_arr[] = name.toCharArray();

        if(name_len < 3)
            throw new NameLengthException();
        
        for(int i = 0; i < name_len; i++) {
            if(!(((int) name_arr[i] >= 65 && (int) name_arr[i] <= 90) || ((int) name_arr[i] >= 97 && (int) name_arr[i] <= 122) || name_arr[i] == 32)) // Verifies if it's NOT a letter (based on their ascii codes)
                throw new IllegalArgumentException("Your name must not contain any symbols or numbers.");
        }



        this.userRepository.save(user);
        
        return "Registered!";
    }
}
