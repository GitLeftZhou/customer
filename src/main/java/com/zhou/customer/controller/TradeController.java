package com.zhou.customer.controller;

import com.zhou.customer.entity.Param;
import com.zhou.customer.entity.Trade;
import com.zhou.customer.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class TradeController {
    @Autowired
    private TradeService localservice;

    @GetMapping("/trades")
    public String list(Model model) {
        List<Trade> trades = localservice.listTrade();
        model.addAttribute("trades", trades);
        return "/customer/trades";
    }

    @GetMapping("/trade")
    public String toAddPage() {
        return "/customer/trade";
    }

    @GetMapping("/trade/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        System.out.println("查询用户信息进行修改");
        Trade obj = localservice.findTradeById(id);
        model.addAttribute("trade", obj);
        return "/customer/trade";
    }

    @DeleteMapping("/trade/{id}")
    public String delete(@PathVariable("id") Integer id) {
        int result = localservice.delete(id);
        return "redirect:/trades";
    }

    @PutMapping("/trade")
    public String update(Trade obj) {
        int result = localservice.update(obj);
        return "redirect:/trades";
    }

    @PostMapping("/trade")
    public String addTrade(Trade obj) {
        String msg = localservice.insert(obj);
        return "redirect:/trades";
    }

    @GetMapping("/trade/mobile")
    public String listByCondition(Param param, Model model) {
        System.out.println("==============" + param.getBgndate());
        List<Trade> trades = localservice.findTradeByDateRange(param);
        model.addAttribute("bgndate", param.getBgndate());
        model.addAttribute("enddate", param.getEnddate());
        model.addAttribute("mobile", param.getMobile());
        model.addAttribute("trades", trades);
        return "/customer/trades";
    }

}
