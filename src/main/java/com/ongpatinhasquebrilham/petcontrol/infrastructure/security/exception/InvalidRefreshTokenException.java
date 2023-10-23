package com.ongpatinhasquebrilham.petcontrol.infrastructure.security.exception;

public class InvalidRefreshTokenException extends InvalidTokenException {

    public InvalidRefreshTokenException(String message) {
        super(message);
    }
}