package com.yingwu.digital.mapper.zb;


import com.yingwu.digital.bean.dto.zb.ZBDepth;

public interface ZBDepthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZBDepth record);

    int insertSelective(ZBDepth record);

    ZBDepth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ZBDepth record);

    int updateByPrimaryKey(ZBDepth record);
}