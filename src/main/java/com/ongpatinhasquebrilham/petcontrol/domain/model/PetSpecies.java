package com.ongpatinhasquebrilham.petcontrol.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PetSpecies {
    DOG("Cão"),
    CAT("Gato");

    private final String species;

    @JsonValue
    public String species() {
        return species;
    }
}