package com.ongpatinhasquebrilham.petcontrol.api.model;

import com.ongpatinhasquebrilham.petcontrol.core.validation.EnumValue;
import com.ongpatinhasquebrilham.petcontrol.domain.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @EnumValue(enumClass = UserRole.class, ignoreCase = true)
    private String role;

}