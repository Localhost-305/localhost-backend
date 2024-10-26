package com.api.domain.entity;

import com.api.domain.dto.UserDto;
import com.api.domain.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name="Users")
@Table(name="users")
@NoArgsConstructor // Implements constructor only: new User(); don't user AllArgsContructor
@Data // Implements GET's and SET's
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String email;
    private String password;
    private LocalDate createdOn;
    private LocalDate updatedOn;

    @Enumerated(EnumType.STRING)
    private Role role;


    public User(@Valid UserDto userDto){
        this.name = userDto.name();
        this.email = userDto.email();
        this.password = userDto.password();
        this.createdOn = userDto.createdOn();
        this.updatedOn = userDto.updateOn();
        this.role = userDto.role();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_SUPERVISOR") ,new SimpleGrantedAuthority("ROLE_USER"));
        else if (this.role == Role.SUPERVISOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_SUPERVISOR"), new SimpleGrantedAuthority("ROLE_USER"));
        } else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return "";
    }
}
