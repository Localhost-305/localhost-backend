package com.api.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name="role")
@Table(name="dim_roles")
@NoArgsConstructor
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "fact_roles_permissions",
    joinColumns = @JoinColumn(name = "role_id"),
    inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;

}
