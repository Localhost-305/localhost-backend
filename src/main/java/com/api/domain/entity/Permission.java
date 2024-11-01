package com.api.domain.entity;

import com.api.domain.dto.PermissionDTO;
import com.api.domain.dto.RoleDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="permission")
@Table(name="dim_permissions")
@NoArgsConstructor
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long permissionid;
    @Column(name = "permission_name")
    private String permissionName;

    public Permission(@Valid PermissionDTO permissionDto) {
        this.permissionName = permissionDto.permissionName();
    }
}
