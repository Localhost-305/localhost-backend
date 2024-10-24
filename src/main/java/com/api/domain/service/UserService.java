package com.api.domain.service;

import com.api.domain.dto.UserDto;
import com.api.domain.entity.Job;
import com.api.domain.entity.User;
import com.api.domain.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {

        List<User> users = repository.findAll();
        return users;
    }

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void save(@Valid UserDto userDto){
        User newUser = new User(userDto);
        String encodedPassword = passwordEncoder.encode(userDto.password());
        newUser.setPassword(encodedPassword);
        newUser.setRoles(userDto.roles()); // Atribui os papéis ao usuário

        repository.save(newUser);
    }

}