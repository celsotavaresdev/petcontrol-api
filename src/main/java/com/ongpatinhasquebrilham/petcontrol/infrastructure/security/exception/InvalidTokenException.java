package com.ongpatinhasquebrilham.petcontrol.infrastructure.security.exception;

import javax.naming.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException(String message) {
        super(message);
    }
}