package com.api.domain.service;

import com.api.domain.dto.UserDto;
import com.api.domain.entity.Role;
import com.api.domain.entity.User;
import com.api.domain.repository.RoleRepository;
import com.api.domain.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void save(@Valid UserDto userDto) {
        User newUser = new User(userDto);
        String encodedPassword = passwordEncoder.encode(userDto.password());
        newUser.setPassword(encodedPassword);

        Role role = roleRepository.findByRoleName(userDto.role())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        newUser.setRole(role);

        repository.save(newUser);

    }

    public void update(Long userId, @Valid UserDto userDto) {
        User existingUser = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userDto.name() != null && !userDto.name().isBlank()) {
            existingUser.setName(userDto.name());
        }
        if (userDto.email() != null && !userDto.email().isBlank()) {
            existingUser.setEmail(userDto.email());
        }

        existingUser.setUpdatedOn(LocalDate.now());

        repository.save(existingUser);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }
}
