package com.ongpatinhasquebrilham.petcontrol.domain.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PetGender {
    MALE("Macho"),
    FEMALE("Fêmea");

    private final String gender;

    @JsonValue
    public String gender() {
        return gender;
    }

}