package com.common.validator.SpecialLevel.constraints;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 字符长度自定义校验
 *
 * @author: wangji
 * @date: 2018-03-28 11:16
 */
public class CharLengthCustomeValidate implements ConstraintValidator<CharLength, String> {

    private int length = 0;

    @Override
    public void initialize(CharLength constraintAnnotation) {
        this.length = constraintAnnotation.length();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = true;

        // 非空的情况下进行字符长度的校验、满足某些特定的可选择的需求
        if (StringUtils.isNotEmpty(value) && ValidatorUtils.getCharLength(value.trim()) > length) {
            result = false;
        }
        return result;
    }
}
