package com.zhou.customer.entity;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Setter
    private int id;

    private String username;

    private String password;

    private String mobile;

    private String connMobile;

    @Getter
    private String status;//0 无效 1 有效


    @Getter
    @Setter
    private String payInfo;

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", payinfo=" + payInfo + ",  mobile=" + mobile + "]";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConnMobile() {
        return connMobile;
    }

    public void setConnMobile(String connMobile) {
        this.connMobile = connMobile;
    }
}
