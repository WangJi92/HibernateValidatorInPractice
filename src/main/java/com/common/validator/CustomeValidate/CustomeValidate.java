package com.common.validator.CustomeValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * descrption: spring中自定义约束其
 */
public class CustomeValidate implements ConstraintValidator<GenderAnnotation, Gender> {

    Gender annotationValue;

    @Override
    public void initialize(GenderAnnotation constraintAnnotation) {
        //获取注解的值
        annotationValue = constraintAnnotation.value();
    }

    /**
     * 校验是否与注解上的值保持一致性
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Gender value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        } else if (!value.getValue().equals(annotationValue.getValue())) {
            return false;
        }
        return true;

    }
}
