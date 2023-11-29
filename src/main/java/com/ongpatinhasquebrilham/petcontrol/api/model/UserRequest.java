package com.ongpatinhasquebrilham.petcontrol.api.model;

import com.ongpatinhasquebrilham.petcontrol.core.validation.EnumValue;
import com.ongpatinhasquebrilham.petcontrol.core.validation.NullOrNotBlank;
import com.ongpatinhasquebrilham.petcontrol.core.validation.group.PostValidation;
import com.ongpatinhasquebrilham.petcontrol.core.validation.group.PutValidation;
import com.ongpatinhasquebrilham.petcontrol.domain.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

    @NotBlank(groups = PostValidation.class)
    @NullOrNotBlank(groups = PutValidation.class)
    private String username;

    @NotBlank(groups = PostValidation.class)
    @NullOrNotBlank(groups = PutValidation.class)
    private String password;

    @NotBlank(groups = PostValidation.class)
    @NullOrNotBlank(groups = PutValidation.class)
    @EnumValue(enumClass = UserRole.class, ignoreCase = true, groups = {PostValidation.class, PutValidation.class})
    private String role;

}