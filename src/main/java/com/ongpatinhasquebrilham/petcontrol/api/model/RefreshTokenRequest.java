package com.ongpatinhasquebrilham.petcontrol.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RefreshTokenRequest {

    private String accessToken;
    private String refreshToken;

}