package com.ongpatinhasquebrilham.petcontrol.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {

    private Long id;
    private String username;
    private String role;

}