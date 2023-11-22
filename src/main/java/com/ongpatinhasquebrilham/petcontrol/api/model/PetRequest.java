package com.ongpatinhasquebrilham.petcontrol.api.model;

import com.ongpatinhasquebrilham.petcontrol.core.validation.EnumValue;
import com.ongpatinhasquebrilham.petcontrol.domain.model.PetGender;
import com.ongpatinhasquebrilham.petcontrol.domain.model.PetSize;
import com.ongpatinhasquebrilham.petcontrol.domain.model.PetSpecies;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PetRequest {

    @NotBlank
    @Size(max = 80)
    private String name;

    @NotBlank
    @EnumValue(enumClass = PetSpecies.class, ignoreCase = true)
    private String species;

    @NotBlank
    @EnumValue(enumClass = PetGender.class, ignoreCase = true)
    private String gender;

    @NotNull
    @PositiveOrZero
    private Integer ageInMonths;

    @NotBlank
    private String breed;

    @NotBlank
    @EnumValue(enumClass = PetSize.class, ignoreCase = true)
    private String size;

    @NotNull
    private double weight;

    @Pattern(regexp = "[0-9]{15}")
    private String microchip;

    private boolean isNeutered;
    private String vaccination;
    private String description;
}