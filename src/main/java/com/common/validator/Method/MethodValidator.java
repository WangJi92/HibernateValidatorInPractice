package com.common.validator.Method;

import com.common.validator.Level.Address1;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 校验构造函数、函数参数、返回值等，这里是处理AOP的基础
 * 【参考文档】(https://diamondfsd.com/article/78fa12cd-b530-4a90-b438-13d5a0c4e26c/)
 *
 * @author: wangji
 * @date: 2018/07/11 14:23
 */
@Slf4j
public class MethodValidator {


    public MethodValidator() {
    }

    /**
     * 校验构造函数
     *
     * @param message
     */
    public MethodValidator(@NotNull(message = "构造不能为空") String message) {
        log.info("Constructor通过校验");
    }

    /**
     * 校验返回值
     *
     * @return
     */
    public
    @NotBlank(message = "不能为空的字符串")
    String validateReturn() {
        return "";
    }


    public void testLevel(Address1 address1, @NotBlank String name) {

    }

    public static void main(String[] args) throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        /**
         * 获取校验方法参数的校验器
         */
        ExecutableValidator validatorMethodParam = validator.forExecutables();
        MethodValidator methodValidator = new MethodValidator();

        //region 校验方法参数只能校验方法上的注解哦，不包含Address内部的注解
        Method method = methodValidator.getClass().getMethod("testLevel", Address1.class, String.class);
        Address1 address1 = new Address1();
        Object[] paramObjects = new Object[]{address1, ""};
        pringValidateStr(validatorMethodParam.validateParameters(methodValidator, method, paramObjects));
        //endregion


        //region 校验方法的返回值，同样也是不包含内部注解
        Method validateReturnMethiod = methodValidator.getClass().getMethod("validateReturn");
        Object returnValue = ReflectionUtils.invokeMethod(validateReturnMethiod, methodValidator);
        pringValidateStr(validatorMethodParam.validateReturnValue(methodValidator, validateReturnMethiod, returnValue));
        //endregion


        Constructor<?> constructor = MethodValidator.class.getConstructor(String.class);
        Object[] constructorsParams = new Object[]{null};
        pringValidateStr(validatorMethodParam.validateConstructorParameters(constructor, constructorsParams));

    }

    public static void pringValidateStr(Set<ConstraintViolation<Object>> set2) {
        for (ConstraintViolation<Object> constraintViolation : set2) {
            log.info("错误：" + constraintViolation.getMessage());
            log.info("字段：" + constraintViolation.getPropertyPath().toString());

        }
    }
}
