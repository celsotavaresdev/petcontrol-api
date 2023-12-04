package com.ongpatinhasquebrilham.petcontrol.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class NoWhitespaceValidator implements ConstraintValidator<NoWhitespace, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String pattern = "^(?=\\S+$).*$";
        return Objects.isNull(value)
                || value.matches(pattern);
    }
}