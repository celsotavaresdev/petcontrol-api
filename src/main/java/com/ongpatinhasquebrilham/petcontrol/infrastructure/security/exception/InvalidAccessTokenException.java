package com.ongpatinhasquebrilham.petcontrol.infrastructure.security.exception;

public class InvalidAccessTokenException extends InvalidTokenException {

    public InvalidAccessTokenException(String message) {
        super(message);
    }
}