package com.ongpatinhasquebrilham.petcontrol.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}