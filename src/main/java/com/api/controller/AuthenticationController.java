package com.api.controller;

import com.api.domain.dto.*;
import com.api.domain.entity.User;
import com.api.domain.repository.UserRepository;
import com.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @RequestMapping("/login")
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginDto loginData){
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password());
        var authentication = manager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        var tokenJWT = tokenService.generateToken(user);
        return ResponseEntity.ok(new DataTokenJWTDTO(tokenJWT, new UserResumeDTO(user.getUserId(), user.getName(), user.getEmail())));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDto data){
        if(this.userRepository.findByEmail(data.email()) != null)return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data);

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
