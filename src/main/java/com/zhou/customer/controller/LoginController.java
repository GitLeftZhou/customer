package com.zhou.customer.controller;

import com.zhou.customer.entity.User;
import com.zhou.customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userservice;

    @RequestMapping("/login")
    public String tologinView() {
        return "login";
    }

    @PostMapping(value = "/sys/login")
    public String login(@RequestParam("mobile") String mobile,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session) {
        User user = new User();
        List<User> users = null;
        if ("admin".equals(mobile)) {
            user.setUsername("admin");
            users = userservice.findByName(user);
        } else {
            user.setMobile(mobile);
            users = userservice.findByMobile(user);
        }
        if (users != null && users.size() > 0) {
            user = users.get(0);
            String userPassword = user.getPassword();
            if (!StringUtils.isEmpty(userPassword) && userPassword.equals(password)) {
                session.setAttribute("loginUser", user);
                return "redirect:/main.html";
            } else if (StringUtils.isEmpty(userPassword)) {
                session.setAttribute("loginUser", user);
                return "redirect:/main.html";
            } else {
                map.put("msg", "用户名或密码错误");
                return "login";
            }
        } else {
            map.put("msg", "用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/test")
    public String test(Map<String, Object> map) {
        map.put("id", "id");
        map.put("name", "name");
        map.put("age", "age");
        map.put("addr", "addr");
        return "customer/list";
    }
}
