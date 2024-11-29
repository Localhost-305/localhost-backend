package com.api.domain.repository;

import com.api.domain.dto.UserDto;
import com.api.domain.entity.User;
import com.api.domain.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Role roleMock;

    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(roleMock.getRoleName()).thenReturn("ADMIN");
        when(roleMock.getPermissions()).thenReturn(List.of());

        mockUser = new User(new UserDto("Test User", "user@example.com", "hashedpassword","1"));
        mockUser.setUserId(1L);
        mockUser.setRole(roleMock);
    }

    @Test
    void shouldFindUserByEmail() {

        when(userRepository.findByEmail("user@example.com")).thenReturn(mockUser);

        UserDetails userDetails = userRepository.findByEmail("user@example.com");

        assertNotNull(userDetails);
        assertEquals("user@example.com", userDetails.getUsername());
        assertEquals("ROLE_ADMIN", userDetails.getAuthorities().stream()
                .findFirst()
                .map(Object::toString)
                .orElse(""));
    }

    @Test
    void shouldReturnNullWhenUserNotFound() {

        when(userRepository.findByEmail("notfound@example.com")).thenReturn(null);

        UserDetails userDetails = userRepository.findByEmail("notfound@example.com");

        assertNull(userDetails);
    }
}
