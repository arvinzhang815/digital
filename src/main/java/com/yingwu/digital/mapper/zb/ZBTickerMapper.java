package com.yingwu.digital.mapper.zb;


import com.yingwu.digital.bean.dto.zb.ZBTicker;

public interface ZBTickerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZBTicker record);

    int insertSelective(ZBTicker record);

    ZBTicker selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZBTicker record);

    int updateByPrimaryKey(ZBTicker record);
}