package com.ongpatinhasquebrilham.petcontrol.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NullOrNotBlankValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NullOrNotBlank {

    String message() default "Must not be blank";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}