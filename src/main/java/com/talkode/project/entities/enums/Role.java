package com.talkode.project.entities.enums;

public enum Role {
    USER("user"),
    ADMIN("role");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
