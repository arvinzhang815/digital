package com.yingwu.digital.mapper.okex;


import com.yingwu.digital.bean.dto.okex.Deals;

public interface OKExDealsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Deals record);

    int insertSelective(Deals record);

    Deals selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deals record);

    int updateByPrimaryKey(Deals record);
}