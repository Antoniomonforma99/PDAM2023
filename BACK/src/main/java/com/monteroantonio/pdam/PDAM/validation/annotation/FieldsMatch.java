package com.monteroantonio.pdam.PDAM.validation.annotation;

import com.monteroantonio.pdam.PDAM.validation.validator.FieldsMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = FieldsMatchValidator.class)
public @interface FieldsMatch {

    String message() default "Los dos campos deben ser iguales.";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String field();
    String fieldMatch();
}
