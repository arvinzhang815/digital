package com.yingwu.digital.mapper.huobi;


import com.yingwu.digital.bean.dto.huobi.TradeDetail;

public interface HuobiTradeDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeDetail record);

    int insertSelective(TradeDetail record);

    TradeDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeDetail record);

    int updateByPrimaryKey(TradeDetail record);
}