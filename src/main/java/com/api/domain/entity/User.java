package com.api.domain.entity;

import com.api.domain.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name="Users")
@Table(name="dim_users")
@NoArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String email;
    private String password;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(@Valid UserDto userDto) {
        this.name = userDto.name();
        this.email = userDto.email();
        this.password = userDto.password();
        this.createdOn = LocalDate.now();
        this.updatedOn = LocalDate.now();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        authorities.addAll(getRole()
        .getPermissions()
        .stream()
        .map(p -> new SimpleGrantedAuthority(p.getPermissionName()))
                        .collect(Collectors.toList()));
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return getEmail();
    }
}
