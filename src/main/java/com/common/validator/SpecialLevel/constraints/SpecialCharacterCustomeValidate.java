package com.common.validator.SpecialLevel.constraints;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  特殊字符校验
 * @author: wangji
 * @date: 2018-03-28 15:51
 */
public class SpecialCharacterCustomeValidate implements ConstraintValidator<SpecialCharacter, String> {
    private String pattern = "";
    @Override
    public void initialize(SpecialCharacter constraintAnnotation) {
        pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = true;
        if(StringUtils.isNotEmpty(value)){
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            result = !m.matches();
        }
        return result;
    }
}
