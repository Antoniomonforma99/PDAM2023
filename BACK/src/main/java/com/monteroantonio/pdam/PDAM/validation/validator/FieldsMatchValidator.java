package com.monteroantonio.pdam.PDAM.validation.validator;

import com.monteroantonio.pdam.PDAM.validation.annotation.FieldsMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.PropertyAccessorFactory;

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
