package com.zhou.customer.service;

import com.zhou.customer.entity.Param;
import com.zhou.customer.entity.Trade;
import com.zhou.customer.entity.User;
import com.zhou.customer.mapper.TradeMapper;
import com.zhou.customer.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class TradeService {

    @Autowired
    private TradeMapper mapper;
    @Autowired
    private UserMapper userMapper;

    public Trade findTradeById(Integer id) {
        Trade trade = mapper.findTradeById(id);
        User user = userMapper.findUserById(trade.getUser().getId());
        trade.setUser(user);
        return trade;
    }

    public List<Trade> findTradeByDateRange(Param param) {
        List<Trade> trades = null;
        if (StringUtils.isEmpty(param.getMobile())
                && null == param.getBgndate()
                && null == param.getEnddate()) {
            trades = mapper.listTrade();
        }
        trades = mapper.findTradeByDateRange(param);
        for (Trade trade : trades) {
            User user = userMapper.findUserById(trade.getUser().getId());
            trade.setUser(user);
        }
        return trades;
    }

    public String insert(Trade trade) {
        List<User> userByMobile = userMapper.findUserByMobile(trade.getUser());
        //如果当前用户电话无用户记录，自动保存一条用户记录
        if (null == userByMobile || userByMobile.size() == 0) {
            trade.getUser().setPassword("123456");
            userMapper.insertUser(trade.getUser());
        } else {
            trade.setUser(userByMobile.get(0));
        }
        if (null == trade.getSellTm()) {
            trade.setSellTm(new Date());
        }
        int suc = mapper.insert(trade);
        return suc == 1 ? "保存成功" : "保存失败";
    }

    public List<Trade> listTrade() {
        List<Trade> trades = mapper.listTrade();
        for (Trade trade : trades) {
            User user = userMapper.findUserById(trade.getUser().getId());
            trade.setUser(user);
        }
        return trades;
    }

    public int update(Trade trade) {
        return mapper.update(trade);
    }

    public int delete(int id) {
        return mapper.delete(id);
    }

}
