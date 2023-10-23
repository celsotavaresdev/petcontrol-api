package com.ongpatinhasquebrilham.petcontrol.api.model;

import com.ongpatinhasquebrilham.petcontrol.domain.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequest {

    private String username;
    private String password;
    private UserRole role;

}