package com.common.validator.SpecialLevel.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 特殊字符自定义校验
 *
 * @author: wangji
 * @date: 2018-03-28 11:16
 *
 * @since  1.8+
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy ={SpecialCharacterCustomeValidate.class})
public @interface SpecialCharacter {
    String message() default "内容不能包含非法字符\\/:*?\"<|'&%>" ;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String pattern() default ".*(\\/|\\*|\\?|\\&|\\%|\\<|\\>|\\')+.*";
}
