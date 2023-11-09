package com.ongpatinhasquebrilham.petcontrol.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PetStatus {
    AVAILABLE("disponível"),
    UNAVAILABLE("Indisponível");

    private final String status;

    PetStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String status() {
        return status;
    }
}