package com.yingwu.digital.mapper.huobi;


import com.yingwu.digital.bean.dto.huobi.MarketDetail;

public interface HuobiMarketDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MarketDetail record);

    int insertSelective(MarketDetail record);

    MarketDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MarketDetail record);

    int updateByPrimaryKey(MarketDetail record);
}