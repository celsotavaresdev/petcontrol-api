package com.ongpatinhasquebrilham.petcontrol.core.security.authorization.exception;

public class InvalidRefreshTokenException extends InvalidTokenException {

    public InvalidRefreshTokenException(String message) {
        super(message);
    }
}