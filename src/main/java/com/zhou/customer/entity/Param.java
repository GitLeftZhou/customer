package com.zhou.customer.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Param {

    @Setter
    private String username;
    @Setter
    private String password;
    @Setter
    private String mobile;
    @Setter
    private Date bgndate;
    @Setter
    private Date enddate;


    public String getMobile() {
        return mobile;
    }

    public Date getBgndate() {
        return bgndate;
    }

    public Date getEnddate() {
        return enddate;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
