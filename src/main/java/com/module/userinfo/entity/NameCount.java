package com.module.userinfo.entity;

/**
 * descrption:
 * authohr: wangji
 * date: 2017-11-01 19:30
 */
public class NameCount {
    public String name;
    public Long count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "NameCount{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
