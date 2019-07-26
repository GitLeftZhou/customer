package com.zhou.customer.mapper;

import java.util.List;

import com.zhou.customer.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findUserById(Integer id);

    List<User> findUserByName(User username);

    List<User> findUserByMobile(User mobile);

    public List<User> listUser();

    public int insertUser(User user);

    public int delete(int id);

    public int deleteSofty(int id);

    public int update(User user);

}
