package com.module.userinfo.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * descrption:
 * authohr: wangji
 * date: 2017-11-01 14:16
 */
@Entity
@Table(name = "work")
public class Work implements Serializable {

    @Column(name ="workName")
    private String name;

    @Column(name ="workAddress")
    private String workAddress;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
