package com.talkode.project.controllers;

import com.talkode.project.config.TokenService;
import com.talkode.project.controllers.dto.AuthenticationDTO;
import com.talkode.project.controllers.dto.RegisterDTO;
import com.talkode.project.entities.CustomUser;
import com.talkode.project.entities.LoginResponseDTO;
import com.talkode.project.exceptions.NameLengthException;
import com.talkode.project.exceptions.PasswordLengthException;
import com.talkode.project.exceptions.UsernameExistsException;
import com.talkode.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Transactional
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO request) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = this.tokenService.generateToken((CustomUser) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO request) {
        String name = request.name();
        String username = request.username();
        String password = request.password();

        if(name.length() == 0)
            throw new NameLengthException();

        if (this.userRepository.findByUsername(username) != null)
            throw new UsernameExistsException();

        if(password.length() < 8)
            throw new PasswordLengthException();

        String hash = new BCryptPasswordEncoder().encode(request.password());
        CustomUser user = new CustomUser(name, username, hash);

        this.userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
