package com.common.validator.Simple;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;



/**
 * <p>简单校验的使用</p>
 * {@linkplain SimpleTest 简单测试类}
 * <P>
 @Valid 被注释的元素是一个对象，需要检查此对象的所有字段值
 @Null 被注释的元素必须为 null
 @NotNull 被注释的元素必须不为 null
 @AssertTrue 被注释的元素必须为 true
 @AssertFalse 被注释的元素必须为 false
 @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 @Size(max, min) 被注释的元素的大小必须在指定的范围内
 @Digits (integer, fraction) 被注释的元素必须是一个数字，其值必须在可接受的范围内
 @Past 被注释的元素必须是一个过去的日期
 @Future 被注释的元素必须是一个将来的日期
 @Pattern(value) 被注释的元素必须符合指定的正则表达式
 * </P>
 */
@Slf4j
public class SimpleValidator {

    @NotNull(message = "id不能为空!")
    @Min(value = 1, message = "Id只能大于等于1，小于等于10")
    @Max(value = 10, message = "Id只能大于等于1，小于等于10")
    private Integer id;

    @NotNull(message = "姓名不能为空!")
    @Size(min = 2, max = 6, message = "姓名长度必须在{min}和{max}之间")
    @Pattern(regexp = "[\u4e00-\u9fa5]+", message = "名称只能输入是中文字符")
    private String userName;

    @NotBlank(message = "不能为空字符串")
    @Size(min = 6, max = 12, message = "密码长度必须在{min}和{max}之间")
    private String passWord;


    @NotNull(message = "邮件不能为空!")
    @Email(message = "邮件格式不正确")
    private String email;

    public Integer getId() {
        return id;
    }

    public SimpleValidator setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public SimpleValidator setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassWord() {
        return passWord;
    }

    public SimpleValidator setPassWord(String passWord) {
        this.passWord = passWord;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SimpleValidator setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userName", userName)
                .append("passWord", passWord)
                .append("email", email)
                .toString();
    }

}

