package com.yingwu.digital.dao.okex;


import com.yingwu.digital.bean.dto.okex.KLine;
import org.springframework.stereotype.Repository;

@Repository
public interface OKExKLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KLine record);

    int insertSelective(KLine record);

    KLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KLine record);

    int updateByPrimaryKey(KLine record);
}