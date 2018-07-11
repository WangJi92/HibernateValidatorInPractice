package com.common.validator.CustomeValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义校验
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomeValidate.class)//处理的类
public @interface GenderAnnotation {

    String message() default "必须是女人";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Gender value() default Gender.Neutrality;
}
