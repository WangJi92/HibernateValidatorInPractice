package com.common.validator.Level;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * descrption:
 * authohr: wangji
 * date: 2018-03-29 15:23
 */
public class Address1 {

    @NotBlank(message = "addressName 不能为空")
    private String  addressName;

    public String getAddressName() {
        return addressName;
    }

    public Address1 setAddressName(String addressName) {
        this.addressName = addressName;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("addressName", addressName)
                .toString();
    }
}
