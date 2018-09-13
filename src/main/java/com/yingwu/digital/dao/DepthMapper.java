package com.yingwu.digital.dao;


import com.yingwu.digital.bean.dto.Depth;
import org.springframework.stereotype.Repository;

@Repository
public interface DepthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Depth record);

    int insertSelective(Depth record);

    Depth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Depth record);

    int updateByPrimaryKey(Depth record);
}