package com.api.controller;

import com.api.domain.dto.UserDto;
import com.api.domain.entity.User;
import com.api.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public  ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody @Valid UserDto userDto){
        userService.save(userDto);
        return ResponseEntity.ok().build();
    }
}
