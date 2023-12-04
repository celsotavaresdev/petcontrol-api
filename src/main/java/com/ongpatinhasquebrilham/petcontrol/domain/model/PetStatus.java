package com.ongpatinhasquebrilham.petcontrol.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public enum PetStatus {
    AVAILABLE("disponível"),
    UNAVAILABLE("indisponível");

    private final String status;

    @JsonValue
    public String status() {
        return status;
    }

    public static Optional<PetStatus> fromString(String value) {
        try {
            return Optional.of(PetStatus.valueOf(value.toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            return Optional.empty();
        }
    }

}