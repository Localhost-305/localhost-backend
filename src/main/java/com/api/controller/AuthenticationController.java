package com.api.controller;

import com.api.domain.dto.DataTokenJWTDTO;
import com.api.domain.dto.LoginDto;
import com.api.domain.dto.UserResumeDTO;
import com.api.domain.entity.User;
import com.api.domain.service.EmailService;
import com.api.domain.service.UserService;
import com.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
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

    @Autowired
    private EmailService emailService;

    private static final int MAX_ATTEMPTS = 3;
    private final ConcurrentHashMap<String, AtomicInteger> failedAttempts = new ConcurrentHashMap<>();

    @RequestMapping("/login")
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginDto loginData) {
        String email = loginData.email();

        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(email, loginData.password());
            var authentication = manager.authenticate(authenticationToken);

            failedAttempts.remove(email);

            User user = (User) authentication.getPrincipal();
            var tokenJWT = tokenService.generateToken(user);

            List<String> permissions = user.getRole().getPermissions().stream()
                    .map(permission -> permission.getPermissionName())
                    .collect(Collectors.toList());

            var userResume = new UserResumeDTO(user.getUserId(), user.getName(), user.getEmail(), user.getRole().getRoleName(), user.getRole().getId(), permissions);

            return ResponseEntity.ok(new DataTokenJWTDTO(tokenJWT, userResume));

        } catch (AuthenticationException ex) {
            handleFailedLoginAttempt(email);
            throw new BadCredentialsException("Credenciais inválidas.");
        }
    }

    /**
     * Lida com tentativas de login falhas.
     *
     * @param email O e-mail do usuário.
     */
    private void handleFailedLoginAttempt(String email) {
        AtomicInteger attempts = failedAttempts.computeIfAbsent(email, k -> new AtomicInteger(0));
        int attemptCount = attempts.incrementAndGet();

        if (attemptCount >= MAX_ATTEMPTS) {
            failedAttempts.remove(email);

            String subject = "Tentativa de acesso à sua conta";
            String message = "Detectamos 3 tentativas de login falhas na sua conta. "
                    + "Se não foi você, por favor, entre em contato com o suporte.";
            emailService.sendEmail(email, subject, message);
        }
    }
}
