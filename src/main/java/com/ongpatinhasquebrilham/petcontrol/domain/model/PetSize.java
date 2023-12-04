package com.ongpatinhasquebrilham.petcontrol.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PetSize {
    SMALL("Pequeno"),
    MEDIUM("Médio"),
    LARGE("Grande");

    private final String size;

    @JsonValue
    public String size() {
        return size;
    }

}