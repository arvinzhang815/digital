package com.yingwu.digital.mapper.huobi;


import com.yingwu.digital.bean.dto.huobi.HuobiEntrustInfo;

public interface HuobiEntrustInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HuobiEntrustInfo record);

    int insertSelective(HuobiEntrustInfo record);

    HuobiEntrustInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HuobiEntrustInfo record);

    int updateByPrimaryKey(HuobiEntrustInfo record);
}