package com.monteroantonio.pdam.PDAM.validation.validator;

import com.monteroantonio.pdam.PDAM.validation.annotation.FieldsDiffer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.PropertyAccessorFactory;

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
