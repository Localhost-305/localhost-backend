package com.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "rolePermission")
@Table(name = "fact_roles_permissions")
@NoArgsConstructor
@Data
public class RolePermission {

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Getter
    @Id
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    public RolePermission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }

}