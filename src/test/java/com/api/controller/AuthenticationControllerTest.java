package com.api.controller;

import com.api.domain.dto.LoginDto;
import com.api.domain.entity.Permission;
import com.api.domain.entity.Role;
import com.api.domain.entity.User;
import com.api.infra.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private TokenService tokenService;

    private User user;
    private Role role;
    private Permission permission;

    @BeforeEach
    void setUp() {
        // Configurar a permissão
        permission = new Permission();
        permission.setPermissionName("READ_PRIVILEGE");

        // Configurar o papel (Role)
        role = new Role();
        role.setId(1L);
        role.setRoleName("ADMIN");
        role.setPermissions(List.of(permission));

        // Configurar o usuário
        user = new User();
        user.setUserId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setRole(role);
    }

    @Test
    void testLoginEndpoint() throws Exception {
        // Mockar a autenticação
        LoginDto loginDto = new LoginDto("john.doe@example.com", "password");
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());

        when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(user, null));

        when(tokenService.generateToken(user)).thenReturn("mocked-jwt-token");

        // Chamada do endpoint e validações
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "email": "john.doe@example.com",
                                    "password": "password"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mocked-jwt-token"))
                .andExpect(jsonPath("$.user.userId").value(1))
                .andExpect(jsonPath("$.user.name").value("John Doe"))
                .andExpect(jsonPath("$.user.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.user.roleName").value("ADMIN"))
                .andExpect(jsonPath("$.user.permissions[0]").value("READ_PRIVILEGE"));
    }
}
