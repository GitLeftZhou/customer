package com.zhou.customer.mapper;

import com.zhou.customer.entity.Param;
import com.zhou.customer.entity.Trade;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TradeMapper {

    Trade findTradeById(Integer id);

    List<Trade> findTradeByDateRange(Param param);

    public List<Trade> listTrade();

    public int insert(Trade trade);

    public int delete(int id);

    public int deleteSofty(int id);

    public int update(Trade trade);

}
