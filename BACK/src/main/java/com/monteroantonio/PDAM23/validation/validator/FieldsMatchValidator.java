package com.monteroantonio.PDAM23.validation.validator;

import com.monteroantonio.PDAM23.validation.annotation.FieldsMatch;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {

    private String field, fieldMatch;


    @Override
    public void initialize(FieldsMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object field = PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(this.field);
        Object fieldMatch = PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(this.fieldMatch);

        return field.equals(fieldMatch);
    }
}