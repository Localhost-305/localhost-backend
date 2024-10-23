package com.api.domain.Enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.api.domain.Enums.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(ALLOWED_TO_SEE, ALLOWED_TO_CHANGE, ALLOWED_TO_IMPORT, ALLOWED_TO_ADD_ROLE, ALLOWED_TO_SEE_MONEY)
    ),
    SUPERVISOR(
            Set.of(ALLOWED_TO_SEE, ALLOWED_TO_IMPORT, ALLOWED_TO_SEE_MONEY)
    ),
    WORKER(
            Set.of(ALLOWED_TO_SEE, ALLOWED_TO_IMPORT)
    );

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}