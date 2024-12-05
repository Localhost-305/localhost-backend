package com.api.controller;

import com.api.domain.dto.UserDto;
import com.api.domain.entity.Role;
import com.api.domain.entity.User;
import com.api.domain.service.UserService;
import com.api.domain.exception.UserNotFoundException;
import com.api.domain.exception.UserRegistrationException;
import com.api.domain.exception.UserUpdateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUsers() {

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());
        expectedUsers.add(new User());

        when(userService.getAllUsers()).thenReturn(expectedUsers);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(ResponseEntity.ok(expectedUsers), response);
    }

    @Test
    public void testRegisterNewUser() {

        UserDto userDto = new UserDto(
                "John Doe",
                "johndoe@example.com",
                "securepassword",
                "USER"
        );

        ResponseEntity<Void> response = userController.register(userDto);

        assertEquals(ResponseEntity.ok().build(), response);
        verify(userService, times(1)).save(userDto);
    }

    @Test
    public void testUpdateUser() {

        Long userId = 1L;
        UserDto userDto = new UserDto(
                "Updated Name",
                "updatedemail@example.com",
                "newpassword",
                "ADMIN"
        );

        ResponseEntity<Void> response = userController.updateUser(userId, userDto);

        assertEquals(ResponseEntity.ok().build(), response);
        verify(userService, times(1)).update(userId, userDto);
    }

    @Test
    public void testUpdateUserRole() {
        Long userId = 1L;
        String roleId = "ADMIN";

        ResponseEntity<Void> response = userController.updateRole(userId, roleId);

        assertEquals(ResponseEntity.ok().build(), response);
        verify(userService, times(1)).updateRole(userId, roleId);
    }

    @Test
    public void testGetRoles() {
        List<Role> expectedRoles = new ArrayList<>();

        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");

        Role userRole = new Role();
        userRole.setRoleName("USER");

        expectedRoles.add(adminRole);
        expectedRoles.add(userRole);

        when(userService.getAllRoles()).thenReturn(expectedRoles);

        ResponseEntity<List<Role>> response = userController.getRoles();

        assertEquals(ResponseEntity.ok(expectedRoles), response);
    }

    @Test
    public void testRegisterInvalidUserThrowsException() {
        UserDto userDto = new UserDto(
                "",
                "invalidemail",
                "123",
                "INVALID_ROLE"
        );

        doThrow(new UserRegistrationException("Invalid user data")).when(userService).save(userDto);

        assertThrows(UserRegistrationException.class, () -> userController.register(userDto));
    }

    @Test
    public void testGetAllUsersThrowsException() {
        when(userService.getAllUsers()).thenThrow(new RuntimeException("Error"));

        assertThrows(UserNotFoundException.class, () -> userController.getAllUsers());
    }

    @Test
    public void testUpdateUserThrowsException() {
        Long userId = 1L;
        UserDto userDto = new UserDto(
                "Updated Name",
                "updatedemail@example.com",
                "newpassword",
                "ADMIN"
        );

        UserUpdateException e = new UserUpdateException(new Exception("Erro simulado"));
        doThrow(new UserUpdateException(e)).when(userService).update(userId, userDto);

        assertThrows(UserUpdateException.class, () -> userController.updateUser(userId, userDto));
    }

    @Test
    public void testGetRolesThrowsException() {
        when(userService.getAllRoles()).thenThrow(new RuntimeException("Error"));

        assertThrows(RuntimeException.class, () -> userController.getRoles());
    }

    @Test
    public void testUpdateUserRoleThrowsException() {
        Long userId = 1L;
        String roleId = "INVALID_ROLE";

        doThrow(new RuntimeException("Invalid role")).when(userService).updateRole(userId, roleId);

        assertThrows(RuntimeException.class, () -> userController.updateRole(userId, roleId));
    }
}
