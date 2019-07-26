package com.zhou.customer.controller;

import com.zhou.customer.entity.Param;
import com.zhou.customer.entity.Report;
import com.zhou.customer.service.ReportService;
import com.zhou.customer.service.ReportService;
import com.zhou.customer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class ReportController {
    @Autowired
    private ReportService localservice;

    @GetMapping("/reports")
    public String list(Model model) {
        List<Report> reports = localservice.listReport();
        model.addAttribute("reports", reports);
        return "/customer/reports";
    }

    @GetMapping("/report/{id}")
    public String toViewPage(@PathVariable("id") Integer id, Model model) {
        Report obj = localservice.findReportById(id);
        model.addAttribute("report", obj);
        return "/customer/report";
    }

    @DeleteMapping("/report/{id}")
    public String delete(@PathVariable("id") Integer id) {
        int result = localservice.delete(id);
        return "redirect:/reports";
    }

    @PostMapping("/report/mobile")
    public String listByCondition(Param param, Model model) {
        List<Report> reports = localservice.findReportByDateRange(param);
        model.addAttribute("bgndate", param.getBgndate());
        model.addAttribute("enddate", param.getEnddate());
        model.addAttribute("mobile", param.getMobile());
        model.addAttribute("reports", reports);
        return "/customer/reports";
    }

    @PostMapping("/report/generate")
    public String generateReport(Param param, Model model) throws Exception {
        //生成报表
        try {
            int i = localservice.generateReport(param);
        } catch (Exception e) {
            throw new Exception("生成绩效信息失败");
        }
        return "redirect:/reports";
    }

}
