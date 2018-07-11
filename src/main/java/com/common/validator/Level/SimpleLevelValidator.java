package com.common.validator.Level;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 简单层级校验
 *
 * @author: wangji
 * @date: 2018/07/11 10:37
 */
public class SimpleLevelValidator {

    @Valid
    @NotNull(message = "地址参数不能为空")
    private Address1 address1;


    /**
     *  @NotEmpty Asserts that the annotated string, collection, map or array is not {@code null} or empty.
     */
    @NotEmpty(message = "参数List数据不能为空")
    private List<String> messages;


    @Valid
    @NotEmpty(message = "参数List数据不能为空")
    private List<Address1> address1s;

    public Address1 getAddress1() {
        return address1;
    }

    public SimpleLevelValidator setAddress1(Address1 address1) {
        this.address1 = address1;
        return this;
    }

    public List<String> getMessages() {
        return messages;
    }

    public SimpleLevelValidator setMessages(List<String> messages) {
        this.messages = messages;
        return this;
    }

    public List<Address1> getAddress1s() {
        return address1s;
    }

    public SimpleLevelValidator setAddress1s(List<Address1> address1s) {
        this.address1s = address1s;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("address1", address1)
                .append("messages", messages)
                .append("address1s", address1s)
                .toString();
    }
}
