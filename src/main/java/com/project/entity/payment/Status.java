package com.project.entity.payment;

public enum Status {
    PASSED("Passed"), FAILED("Failed");

    String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
