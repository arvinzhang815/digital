package com.yingwu.digital.mapper.zb;


import com.yingwu.digital.bean.dto.zb.ZBTradeDetail;

public interface ZBTradeDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZBTradeDetail record);

    int insertSelective(ZBTradeDetail record);

    ZBTradeDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZBTradeDetail record);

    int updateByPrimaryKey(ZBTradeDetail record);
}