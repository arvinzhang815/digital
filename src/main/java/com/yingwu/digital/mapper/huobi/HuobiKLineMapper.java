package com.yingwu.digital.mapper.huobi;


import com.yingwu.digital.bean.dto.huobi.KLine;
import org.springframework.stereotype.Repository;

@Repository
public interface HuobiKLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KLine record);

    int insertSelective(KLine record);

    KLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KLine record);

    int updateByPrimaryKey(KLine record);
}