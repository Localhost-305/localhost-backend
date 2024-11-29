package com.api.domain.repository;

import com.api.domain.entity.Role;
import com.api.domain.entity.Permission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class RoleRepositoryTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private Permission permissionMock;

    private Role mockRole;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(permissionMock.getPermissionName()).thenReturn("allowed_to_see");
        mockRole = new Role();
        mockRole.setId(1L);
        mockRole.setRoleName("ADMIN");
        mockRole.setPermissions(List.of(permissionMock));
    }

    @Test
    void shouldFindRoleByRoleName() {
        when(roleRepository.findByRoleName("ADMIN")).thenReturn(Optional.of(mockRole));

        Optional<Role> role = roleRepository.findByRoleName("ADMIN");

        assertTrue(role.isPresent());
        assertEquals("ADMIN", role.get().getRoleName());
    }

    @Test
    void shouldReturnEmptyWhenRoleNotFound() {
        when(roleRepository.findByRoleName("NON_EXISTENT")).thenReturn(Optional.empty());

        Optional<Role> role = roleRepository.findByRoleName("NON_EXISTENT");

        assertFalse(role.isPresent());
    }
}
