package com.common.validator.ScriptAssert;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 比较复杂的校验无法通过注解处理通过调用脚本处理
 * [Validation @ScriptAssert with access to static methods](https://stackoverflow.com/questions/7149713/validation-scriptassert-with-access-to-static-methods)
 * [SpringMVC 使用JSR-303进行校验Bean Validation------通过脚本验证@ScriptAssert](https://blog.csdn.net/forwujinwei/article/details/79363307)
 * [java 调用js 方法库进行简单的数学表达式的运算](https://blog.csdn.net/hjm824/article/details/50606775)
 * [利用JEXL实现动态表达式编译](https://blog.csdn.net/u012468264/article/details/56679802)
 * [在Java中直接调用js代码](https://blog.csdn.net/z834410038/article/details/70231544)
 * [采用Rhino在JAVA中运行JavaScript](https://blog.csdn.net/fengshuiyue/article/details/72134932)
 *
 * @author: wangji
 * @date: 2018/07/11 11:49
 */
@ScriptAssert(lang = "javascript", script = "_this.checkParams(_this.first,_this.second)", message = "校验不通过，第一个比第二个大ya")
@Slf4j
public class ScriptAssertValidator {

    @NotNull
    private Integer first;

    @NotNull
    private Integer second;


    public boolean checkParams(Integer first, Integer second) {
        if (first != null && second != null) {

            if (first.compareTo(second) > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        ScriptAssertValidator groupValidator = new ScriptAssertValidator();
        groupValidator.setFirst(15);
        groupValidator.setSecond(10);
        pringValidateStr(validator.validate(groupValidator));

    }

    public static void pringValidateStr(Set<ConstraintViolation<Object>> set2) {
        for (ConstraintViolation<Object> constraintViolation : set2) {
            log.info("错误：" + constraintViolation.getMessage());
            log.info("字段：" + constraintViolation.getPropertyPath().toString());

        }
    }

    public Integer getFirst() {
        return first;
    }

    public ScriptAssertValidator setFirst(Integer first) {
        this.first = first;
        return this;
    }

    public Integer getSecond() {
        return second;
    }

    public ScriptAssertValidator setSecond(Integer second) {
        this.second = second;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("first", first)
                .append("second", second)
                .toString();
    }
}
