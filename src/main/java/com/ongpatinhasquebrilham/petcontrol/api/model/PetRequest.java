package com.ongpatinhasquebrilham.petcontrol.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PetRequest {

    @NotBlank
    @Size(max = 80)
    private String name;

    @NotBlank
    private String species;

    @NotBlank
    private String gender;

    @NotNull
    @PositiveOrZero
    private Integer ageInMonths;

    @NotBlank
    private String breed;

    @NotBlank
    private String size;

    @NotNull
    private double weight;

    @Pattern(regexp = "[0-9]{15}")
    private String microchip;

    private boolean isNeutered;
    private String vaccination;
    private String description;
}