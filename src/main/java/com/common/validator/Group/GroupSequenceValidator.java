package com.common.validator.Group;

import com.common.validator.Group.Identification.First;
import com.common.validator.Group.Identification.Second;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * 校验分组校验顺序(represents the {@link Default} group for that class.),最后定义的class一定是当前类
 * <p> 通过@GroupSequence指定验证顺序,先验证First分组，如果有错误立即返回
 * 而不会验证Second分组，接着如果First分组验证通过了， 那么才去验证Second分组，最后指定GroupSequenceValidator.class (当前类)
 * 表示那些没有分组的在最后
 * </p>
 *
 * @author: wangji
 * @date: 2018/07/11 11:29
 */
@GroupSequence({First.class,Second.class,GroupSequenceValidator.class})
@Slf4j
public class GroupSequenceValidator {

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
        GroupSequenceValidator groupValidator = new GroupSequenceValidator();
        groupValidator.setUserName("ww");
        groupValidator.setPassWord("ww");
        pringValidateStr(validator.validate(groupValidator, Default.class));

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

    public GroupSequenceValidator setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassWord() {
        return passWord;
    }

    public GroupSequenceValidator setPassWord(String passWord) {
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
