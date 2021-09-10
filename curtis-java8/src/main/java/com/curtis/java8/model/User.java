package com.curtis.java8.model;

import java.math.BigDecimal;

public class User {

    private Integer id;

    private String name;

    private Long phone;

    private Boolean sex;

    private BigDecimal height;

    public User() {
    }

    public User(Integer id, String name, Long phone, Boolean sex, BigDecimal height) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", sex=" + sex +
                ", height=" + height +
                '}';
    }
}
