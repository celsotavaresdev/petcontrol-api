package com.ongpatinhasquebrilham.petcontrol.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {

    private Set<String> acceptedValues = new HashSet<>();
    private boolean ignoreCase;

    @Override
    public void initialize(EnumValue annotation) {
        ignoreCase = annotation.ignoreCase();

        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(e -> ignoreCase ? e.name().toUpperCase() : e.name())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Objects.isNull(value)
                || acceptedValues.contains(ignoreCase ? value.toUpperCase() : value);
    }

}