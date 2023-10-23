package com.ongpatinhasquebrilham.petcontrol.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PetResponse {

    private Long id;
    private String name;
    private String species;
    private String gender;
    private Integer ageInMonths;
    private String breed;
    private String size;
    private double weight;
    private String microchip;
    private boolean isNeutered;
    private String vaccination;
    private String description;

}