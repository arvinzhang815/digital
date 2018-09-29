package com.yingwu.digital.mapper.zb;


import com.yingwu.digital.bean.dto.zb.ZBKLine;

public interface ZBKLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZBKLine record);

    int insertSelective(ZBKLine record);

    ZBKLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZBKLine record);

    int updateByPrimaryKey(ZBKLine record);
}