package com.ongpatinhasquebrilham.petcontrol.core.security.authorization.exception;

import javax.naming.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {

    public InvalidTokenException(String message) {
        super(message);
    }
}