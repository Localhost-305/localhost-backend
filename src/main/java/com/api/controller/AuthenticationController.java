package com.api.controller;

import com.api.domain.dto.*;
import com.api.domain.entity.User;
import com.api.domain.repository.UserRepository;
import com.api.domain.service.UserService;
import com.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping("/login")
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginDto loginData) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password());
        var authentication = manager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        var tokenJWT = tokenService.generateToken(user);

        List<String> permissions = user.getRole().getPermissions().stream()
                .map(permission -> permission.getPermissionName())
                .collect(Collectors.toList());


        var userResume = new UserResumeDTO(user.getUserId(), user.getName(), user.getEmail(), user.getRole().getRoleName(), user.getRole().getId(), permissions);

        return ResponseEntity.ok(new DataTokenJWTDTO(tokenJWT, userResume));
    }

    @PreAuthorize("hasAnyAuthority('allowed_to_change')")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserDto userDto){

        userService.save(userDto);

        return ResponseEntity.ok().build();
    }
}
