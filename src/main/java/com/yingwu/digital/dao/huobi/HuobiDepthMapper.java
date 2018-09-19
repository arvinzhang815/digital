package com.yingwu.digital.dao.huobi;


import com.yingwu.digital.bean.dto.huobi.Depth;
import org.springframework.stereotype.Repository;

@Repository
public interface HuobiDepthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Depth record);

    int insertSelective(Depth record);

    Depth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Depth record);

    int updateByPrimaryKey(Depth record);
}