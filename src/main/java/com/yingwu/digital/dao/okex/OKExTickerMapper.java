package com.yingwu.digital.dao.okex;


import com.yingwu.digital.bean.dto.okex.Ticker;

public interface OKExTickerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ticker record);

    int insertSelective(Ticker record);

    Ticker selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ticker record);

    int updateByPrimaryKey(Ticker record);
}