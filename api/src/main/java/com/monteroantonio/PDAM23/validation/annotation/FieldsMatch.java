package com.monteroantonio.PDAM23.validation.annotation;

import com.monteroantonio.PDAM23.validation.validator.FieldsMatchValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
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