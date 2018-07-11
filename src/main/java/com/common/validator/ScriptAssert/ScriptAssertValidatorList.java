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
 * List脚本
 *
 * @author: wangji
 * @date: 2018/07/11 13:57
 */
@ScriptAssert.List(value = {
        @ScriptAssert(lang = "javascript", script = "_this.checkParams(_this.first,_this.second)", message = "校验不通过，第一个比第二个大"),
        @ScriptAssert(lang = "javascript", script = "_this.bigThan(_this.first)", message = "第一个要大于20")
})
@Slf4j
public class ScriptAssertValidatorList {
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

    public boolean bigThan(Integer first) {
        if (first != null) {
            if (first < 20) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        ScriptAssertValidatorList groupValidator = new ScriptAssertValidatorList();
        groupValidator.setFirst(12);
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

    public ScriptAssertValidatorList setFirst(Integer first) {
        this.first = first;
        return this;
    }

    public Integer getSecond() {
        return second;
    }

    public ScriptAssertValidatorList setSecond(Integer second) {
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



