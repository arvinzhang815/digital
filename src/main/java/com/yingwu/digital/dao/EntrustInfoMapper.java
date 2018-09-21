package com.yingwu.digital.dao;

import com.yingwu.digital.bean.dto.EntrustInfo;

public interface EntrustInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EntrustInfo record);

    int insertSelective(EntrustInfo record);

    EntrustInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EntrustInfo record);

    int updateByPrimaryKey(EntrustInfo record);
}