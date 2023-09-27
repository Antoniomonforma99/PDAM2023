package com.monteroantonio.PDAM23.validation.annotation;

import com.monteroantonio.PDAM23.validation.validator.FieldsDifferValidator;
import javax.validation.Constraint;
import javax.validation.Payload;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = FieldsDifferValidator.class)
public @interface FieldsDiffer {

    String message() default "Los dos campos deben ser diferentes.";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

    String field();
    String fieldDiffer();
}