package com.common.validator.SpecialLevel.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 字符长度自定义校验
 * @author: wangji
 * @date: 2018-03-28 11:16
 *
 * @since  1.8+
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CharLengthCustomeValidate.class)//处理的类
public @interface CharLength {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int length() default 64;
}
