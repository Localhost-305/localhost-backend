package com.api.domain.service;

import com.api.domain.dto.PermissionDTO;
import com.api.domain.dto.UserDto;
import com.api.domain.entity.Permission;
import com.api.domain.entity.User;
import com.api.domain.entity.Role;
import com.api.domain.repository.RoleRepository;
import com.api.domain.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void save(@Valid UserDto userDto) {
        User newUser = new User(userDto);
        String encodedPassword = passwordEncoder.encode(userDto.password());
        newUser.setPassword(encodedPassword);

        // Buscando a role pelo nome
        Role role = roleRepository.findByRoleName(userDto.role())
                .orElseThrow(() -> new RuntimeException("Role not found")); // Verifica se a role existe

        newUser.setRole(role);

        repository.save(newUser);

    }


    public List<User> getAllUsers(){
        return repository.findAll();
    }
}
