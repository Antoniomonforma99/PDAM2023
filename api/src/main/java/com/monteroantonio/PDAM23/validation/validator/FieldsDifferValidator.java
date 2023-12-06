package com.monteroantonio.PDAM23.validation.validator;

import com.monteroantonio.PDAM23.validation.annotation.FieldsDiffer;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsDifferValidator implements ConstraintValidator<FieldsDiffer, Object> {

    private String field, fieldDiffer;


    @Override
    public void initialize(FieldsDiffer constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldDiffer = constraintAnnotation.fieldDiffer();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object field = PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(this.field);
        Object fieldDiffer = PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(this.fieldDiffer);

        return !field.equals(fieldDiffer);
    }
}