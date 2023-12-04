package com.ongpatinhasquebrilham.petcontrol.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    @JsonValue
    public String role() {
        return role;
    }

}