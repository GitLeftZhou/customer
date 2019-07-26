package com.zhou.customer.service;

import com.zhou.customer.entity.Param;
import com.zhou.customer.entity.Report;
import com.zhou.customer.entity.User;
import com.zhou.customer.mapper.ReportMapper;
import com.zhou.customer.mapper.TradeMapper;
import com.zhou.customer.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    private ReportMapper mapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TradeMapper tradeMapper;

    public Report findReportById(Integer id) {
        Report report = mapper.findReportById(id);
        User user = userMapper.findUserById(report.getUser().getId());
        report.setUser(user);
        return report;
    }

    public List<Report> findReportByDateRange(Param param) {
        List<Report> reports = null;
        if (StringUtils.isEmpty(param.getMobile())
                && null == param.getBgndate()
                && null == param.getEnddate()) {
            reports = mapper.listReport();
        }
        reports = mapper.findReport(param);
        for (Report report : reports) {
            User user = userMapper.findUserById(report.getUser().getId());
            report.setUser(user);
        }
        return reports;
    }

    public List<Report> listReport() {
        List<Report> reports = mapper.listReport();
        for (Report report : reports) {
            User user = userMapper.findUserById(report.getUser().getId());
            report.setUser(user);
        }
        return reports;
    }

    public int delete(int id) {
        return mapper.delete(id);
    }

    public int generateReport(Param param) {
        logger.debug("generateReport");
        //查询所有3个月无销售记录的用户

        //将用户状态置为无效

        //
        mapper.generateReport(param);
        return 1;
    }


}
