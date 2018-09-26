package com.yingwu.digital.mapper.okex;


import com.yingwu.digital.bean.dto.okex.Depth;
import org.springframework.stereotype.Repository;

@Repository
public interface OKExDepthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Depth record);

    int insertSelective(Depth record);

    Depth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Depth record);

    int updateByPrimaryKey(Depth record);
}