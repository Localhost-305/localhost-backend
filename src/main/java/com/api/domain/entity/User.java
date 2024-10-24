package com.api.domain.entity;

import com.api.domain.Enums.Role;
import com.api.domain.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import jakarta.persistence.ElementCollection;

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

    @ElementCollection(fetch = FetchType.EAGER) // Permite buscar os papéis associados ao usuário
    @Enumerated(EnumType.STRING)
    private Set<Role> roles; // Altera para um conjunto de papéis

    public User(@Valid UserDto userDto){
        this.email = userDto.email();
        this.password = userDto.password();
        // Aqui você pode atribuir os papéis, se necessário
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .flatMap(role -> role.getAuthorities().stream())
                .collect(Collectors.toList());
    }
    @Override
    public String getUsername() {
        return email; // or another unique identifier
    }
}
