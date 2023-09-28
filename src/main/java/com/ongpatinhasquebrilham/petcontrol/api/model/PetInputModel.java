package com.ongpatinhasquebrilham.petcontrol.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PetInputModel {
    private String name;
    private String species;
    private String gender;
    private Integer ageInMonths;
    private String breed;
    private String size;
    private Integer weight;
    private boolean isNeutered;
    private String microchip;
    private String vaccination;
    private String description;
}