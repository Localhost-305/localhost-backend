package com.api.controller;

import com.api.domain.dto.UserDto;
import com.api.domain.entity.Role;
import com.api.domain.entity.User;
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
    public  ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasAnyAuthority('allowed_to_change')")
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Validated(UserDto.Create.class) UserDto userDto){
        userService.save(userDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('allowed_to_change')")
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId,@RequestBody @Validated(UserDto.Update.class) UserDto userDto) {
        userService.update(userId, userDto);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('allowed_to_change')")
    @PutMapping("/{userId}/role")
    public ResponseEntity<Void> updateRole(@PathVariable Long userId, @RequestBody String roleId) {
        System.out.println(roleId);
        userService.updateRole(userId, roleId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok(userService.getAllRoles());
    }
}
