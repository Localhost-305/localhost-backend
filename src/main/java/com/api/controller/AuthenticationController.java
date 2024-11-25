package com.api.controller;

import com.api.domain.dto.DataTokenJWTDTO;
import com.api.domain.dto.LoginDto;
import com.api.domain.dto.UserResumeDTO;
import com.api.domain.entity.User;
import com.api.domain.service.UserService;
import com.api.infra.security.TokenService;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    Counter authUserSucess;
    Counter authUserErrors;

    public AuthenticationController(MeterRegistry registry){
        authUserSucess = Counter.builder("auth_user_sucess")
            .description("usuarios autenticados")
            .register(registry);

        authUserErrors = Counter.builder("auth_user_error")
            .description("erros de login")
            .register(registry);
    }

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping("/login")
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginDto loginData) {
        try{
            var authenticationToken = new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password());
            var authentication = manager.authenticate(authenticationToken);
            User user = (User) authentication.getPrincipal();
            var tokenJWT = tokenService.generateToken(user);

            List<String> permissions = user.getRole().getPermissions().stream()
                    .map(permission -> permission.getPermissionName())
                    .collect(Collectors.toList());


            var userResume = new UserResumeDTO(user.getUserId(), user.getName(), user.getEmail(), user.getRole().getRoleName(), user.getRole().getId(), permissions);

            // Incrementar a métrica de sucesso
            authUserSucess.increment();

            return ResponseEntity.ok(new DataTokenJWTDTO(tokenJWT, userResume));
        } catch (Exception e) {
            authUserErrors.increment();

            // Retornar resposta de erro
            return ResponseEntity.status(401).body("Erro de autenticação: " + e.getMessage());
        }
    }
}
