package com.zhou.customer.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Trade {

    @Getter
    @Setter
    private int id;

    private User user;

    @Getter
    @Setter
    private Double sellNum;

    @Getter
    @Setter
    private String bookinCnm;

    @Getter
    @Setter
    private String status;//0 无效 1 有效

    private Date sellTm;

    public void setSellTm(Date sellTm) {
        this.sellTm = sellTm;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Date getSellTm() {
        return sellTm;
    }
}
