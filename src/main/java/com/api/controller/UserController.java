package com.api.controller;

import com.api.domain.dto.UserDto;
import com.api.domain.entity.Role;
import com.api.domain.entity.User;
import com.api.domain.exception.UserNotFoundException;
import com.api.domain.exception.UserRegistrationException;
import com.api.domain.exception.UserUpdateException;
import com.api.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('allowed_to_change')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            throw new UserNotFoundException(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('allowed_to_change')")
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Validated(UserDto.Create.class) UserDto userDto){
        try{
            userService.save(userDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new UserRegistrationException("Error while register new user", e);
        }
    }

    @PreAuthorize("hasAnyAuthority('allowed_to_change')")
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId,@RequestBody @Validated(UserDto.Update.class) UserDto userDto) {
        try {
            userService.update(userId, userDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new UserUpdateException(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('allowed_to_change')")
    @PutMapping("/{userId}/role")
    public ResponseEntity<Void> updateRole(@PathVariable Long userId, @RequestBody String roleId) {

        try{
            System.out.println(roleId);
            userService.updateRole(userId, roleId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new UserUpdateException(e);
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(userService.getAllRoles());
    }
}
