package com.ongpatinhasquebrilham.petcontrol.api.model;

import com.ongpatinhasquebrilham.petcontrol.core.validation.EnumValue;
import com.ongpatinhasquebrilham.petcontrol.core.validation.NullOrNotBlank;
import com.ongpatinhasquebrilham.petcontrol.core.validation.group.PostValidation;
import com.ongpatinhasquebrilham.petcontrol.core.validation.group.PutValidation;
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

    @NotBlank(groups = PostValidation.class)
    @NullOrNotBlank(groups = PutValidation.class)
    @Size(max = 80, groups = {PostValidation.class, PutValidation.class})
    private String name;

    @NotBlank(groups = PostValidation.class)
    @NullOrNotBlank(groups = PutValidation.class)
    @EnumValue(enumClass = PetSpecies.class, ignoreCase = true, groups = {PostValidation.class, PutValidation.class})
    private String species;

    @NotBlank(groups = PostValidation.class)
    @NullOrNotBlank(groups = PutValidation.class)
    @EnumValue(enumClass = PetGender.class, ignoreCase = true, groups = {PostValidation.class, PutValidation.class})
    private String gender;

    @NotNull(groups = PostValidation.class)
    @PositiveOrZero(groups = {PostValidation.class, PutValidation.class})
    private Integer ageInMonths;

    @NotBlank(groups = PostValidation.class)
    @NullOrNotBlank(groups = PutValidation.class)
    private String breed;

    @NotBlank(groups = PostValidation.class)
    @NullOrNotBlank(groups = PutValidation.class)
    @EnumValue(enumClass = PetSize.class, ignoreCase = true, groups = {PostValidation.class, PutValidation.class})
    private String size;

    @NotNull(groups = PostValidation.class)
    @Digits(integer = 3, fraction = 2, groups = {PostValidation.class, PutValidation.class})
    private double weight;

    @Pattern(regexp = "[0-9]{15}", groups = {PostValidation.class, PutValidation.class})
    private String microchip;

    private boolean isNeutered;
    private String vaccination;
    private String description;
}