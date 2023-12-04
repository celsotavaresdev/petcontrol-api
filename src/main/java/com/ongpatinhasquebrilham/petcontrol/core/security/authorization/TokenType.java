package com.ongpatinhasquebrilham.petcontrol.core.security.authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenType {
    ACCESS("at+jwt"),
    REFRESH("rt+jwt");

    private final String value;

}