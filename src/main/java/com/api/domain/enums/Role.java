package com.api.domain.enums;

public enum Role {

    ADMIN("admin"),
    SUPERVISOR("supervisor"),
    USER("user");

    private String role;

    Role(String role){
        this.role = role;
    }
}
