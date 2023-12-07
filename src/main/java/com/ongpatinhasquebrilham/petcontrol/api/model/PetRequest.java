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
    @DecimalMax(value = "99.99", groups = {PostValidation.class, PutValidation.class})
    @DecimalMin(value = "0.01", groups = {PostValidation.class, PutValidation.class})
    @Digits(integer = 2, fraction = 2, groups = {PostValidation.class, PutValidation.class})
    private Double weight;

    @Pattern(regexp = "^$|[0-9]{15}", groups = {PostValidation.class, PutValidation.class})
    private String microchip;

    private boolean neutered;
    private String vaccination;
    private String description;

    public void setSpecies(String species) {
        this.species = species.trim().toUpperCase();
    }

    public void setGender(String gender) {
        this.gender = gender.trim().toUpperCase();
    }

    public void setSize(String size) {
        this.size = size.trim().toUpperCase();
    }
}