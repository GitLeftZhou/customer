package com.zhou.customer.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Report {

    @Getter
    @Setter
    private int id;
    private User user;//用户信息
    @Getter
    @Setter
    private Date calcBgnTm;//统计起期

    @Getter
    @Setter
    private Date calcEndTm;//统计止期

    @Getter
    @Setter
    private Date generateReportTm;//报表生成时间

    @Getter
    @Setter
    private Double sellNum;//周期内销售业绩

    @Getter
    @Setter
    private Double groupSellNum;//周期内团队销售业绩

    @Getter
    @Setter
    private Double basePerformance;//基础绩效

    @Getter
    @Setter
    private Double groupPerformance;//团队绩效

    @Getter
    @Setter
    private Double totalPerformance;//总绩效

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
