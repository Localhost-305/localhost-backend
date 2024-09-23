package com.api.domain.entity;

import com.api.domain.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity(name="Users")
@Table(name="users")
@NoArgsConstructor // Implements constructor only: new User(); don't user AllArgsContructor
@Data // Implements GET's and SET's
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String name;
    private String email;
    private String password;
    @Column(name = "created_on") // use only to convert Camel Case (createdOn) to Snake Case (created_on)
    private LocalDate createdOn;
    @Column(name = "updated_on")
    private LocalDate updatedOn;

    public User(@Valid UserDto userDto){
        this.email = userDto.email();
        this.password = userDto.password();
        this.createdOn = LocalDate.now();
        this.updatedOn = LocalDate.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return "";
    }
}
