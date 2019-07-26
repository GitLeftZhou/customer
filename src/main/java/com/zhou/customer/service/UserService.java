package com.zhou.customer.service;

import java.util.List;

import com.zhou.customer.entity.User;
import com.zhou.customer.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(Integer id) {
        return userMapper.findUserById(id);
    }

    public List<User> findByName(User username) {
        return userMapper.findUserByName(username);
    }

    public List<User> findByMobile(User mobile) {
        return userMapper.findUserByMobile(mobile);
    }

    public int insertUser(User user) {
        //防止相同手机号用户注册
        List<User> userByMobile = userMapper.findUserByMobile(user);
        int suc = 0;
        if (null == userByMobile || userByMobile.isEmpty()) {
            if (StringUtils.isEmpty(user.getPassword())) {
                user.setPassword("123456");
            }
            suc = userMapper.insertUser(user);
        }
        return suc;
    }

    public List<User> listUser() {
        return userMapper.listUser();
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    public int delete(int id) {
        return userMapper.delete(id);
    }

    public int destroyUser(User user) {
        return userMapper.deleteSofty(user.getId());
    }
}
