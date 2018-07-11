package com.common.validator.Group;

import com.common.validator.Group.Identification.First;
import com.common.validator.Group.Identification.Second;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * 分组校验 [参考文档](http://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#preface)
 * [参考文档](http://jinnianshilongnian.iteye.com/blog/1990081)
 *
 * @author: wangji
 * @date: 2018/07/11 11:23
 */
@Slf4j
public class GroupValidator {

    @NotNull(message = "姓名不能为空!")
    @Size(min = 2, max = 4, message = "姓名长度必须在{min}和{max}之间", groups = {First.class})
    @Pattern(regexp = "[\u4e00-\u9fa5]+", message = "名称只能输入是中文字符", groups = {First.class})
    private String userName;

    @NotNull(message = "密码不能为空!", groups = {Second.class})
    @Size(min = 6, max = 12, message = "密码长度必须在{min}和{max}之间", groups = {Second.class})
    private String passWord;

    public static void main(String[] args) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        GroupValidator groupValidator = new GroupValidator();
        pringValidateStr(validator.validate(groupValidator, Default.class));

        groupValidator.setUserName("ww");
        pringValidateStr(validator.validate(groupValidator, First.class));

        groupValidator.setPassWord("ww");
        pringValidateStr(validator.validate(groupValidator, Second.class));
    }


    public static void pringValidateStr(Set<ConstraintViolation<Object>> set2) {
        for (ConstraintViolation<Object> constraintViolation : set2) {
            log.info("错误：" + constraintViolation.getMessage());
            log.info("字段：" + constraintViolation.getPropertyPath().toString());

        }
    }

    public String getUserName() {
        return userName;
    }

    public GroupValidator setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassWord() {
        return passWord;
    }

    public GroupValidator setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userName", userName)
                .append("passWord", passWord)
                .toString();
    }
}
