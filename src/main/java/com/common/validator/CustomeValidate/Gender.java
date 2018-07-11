package com.common.validator.CustomeValidate;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * descrption: 男女
 */
public enum Gender {
    Woman("女人", 1),
    Man("男人", 2),
    Neutrality("中立", 3);
    private String F;
    private Integer value;

    Gender(String f, Integer value) {
        F = f;
        this.value = value;
    }

    public String getF() {
        return F;
    }

    public Gender setF(String f) {
        F = f;
        return this;
    }

    public Integer getValue() {
        return value;
    }

    public Gender setValue(Integer value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("F", F)
                .append("value", value)
                .toString();
    }
}
