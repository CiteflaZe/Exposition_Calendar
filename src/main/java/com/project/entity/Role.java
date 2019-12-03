package com.project.entity;

public enum Role {
    ADMIN("Admin"), USER("User");

    String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
