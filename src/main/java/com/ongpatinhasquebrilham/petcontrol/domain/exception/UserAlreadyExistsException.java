package com.ongpatinhasquebrilham.petcontrol.domain.exception;

public class UserAlreadyExistsException extends DomainException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
