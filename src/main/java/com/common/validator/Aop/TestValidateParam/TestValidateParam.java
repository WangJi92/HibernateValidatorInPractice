package com.common.validator.Aop.TestValidateParam;

import java.lang.annotation.*;

/**
 * Created by wangji on 2017/8/14.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestValidateParam {

}
