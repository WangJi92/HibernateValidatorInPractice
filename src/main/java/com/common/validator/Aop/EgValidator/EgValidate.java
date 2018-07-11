package com.common.validator.Aop.EgValidator;

import javax.validation.groups.Default;
import java.lang.annotation.*;

/**
 * Created by wangji on 2018/3/13.
 */
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EgValidate {
    Class<?> [] groups() default {Default.class};
}
