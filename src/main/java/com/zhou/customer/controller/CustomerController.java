package com.zhou.customer.controller;

import com.zhou.customer.entity.User;
import com.zhou.customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private UserService userservice;

    @GetMapping("/users")
    public String list(Model model) {
        List<User> users = userservice.listUser();
        /*User user = new User();
        user.setMobile("123456789");
        user.setUsername("张三");
        user.setStatus("1");
        users.add(user);*/
        model.addAttribute("users", users);
        return "/customer/users";
    }

    @GetMapping("/users/mobile")
    public String listByCondition(User user, Model model) {
        List<User> users = null;
        if (!StringUtils.isEmpty(user.getMobile())) {
            users = userservice.findByMobile(user);
            model.addAttribute("paramMobile", user.getMobile());
        } else if (!StringUtils.isEmpty(user.getUsername())) {
            users = userservice.findByName(user);
            model.addAttribute("paramUsername", user.getUsername());
        } else {
            users = userservice.listUser();
        }
        model.addAttribute("users", users);
        return "/customer/users";
    }

    @GetMapping("/user")
    public String toAddPage() {
        return "/customer/user";
    }

    @GetMapping("/user/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        System.out.println("查询用户信息进行修改");
        User user = userservice.findById(id);
        System.out.println(user);
        model.addAttribute("user", user);
        return "/customer/user";
    }

    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Integer id) {
        int result = userservice.delete(id);
        return "redirect:/users";
    }

    @PutMapping("/user")
    public String update(User user) {
        int result = userservice.update(user);
//		if (result >= 1) {
//			return "修改成功";
//		} else {
//			return "修改失败";
//		}
        return "redirect:/users";
    }

    @PostMapping("/user")
    public String addUser(User user) throws Exception {
        try {
            userservice.insertUser(user);
        } catch (Exception e) {
            throw new Exception("保存用户信息失败");
        }
        return "redirect:/users";
    }

}
