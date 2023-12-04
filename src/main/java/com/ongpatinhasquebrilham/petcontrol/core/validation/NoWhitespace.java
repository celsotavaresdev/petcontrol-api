package com.ongpatinhasquebrilham.petcontrol.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NoWhitespaceValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface NoWhitespace {

    String message() default "Value must not contain whitespaces";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
