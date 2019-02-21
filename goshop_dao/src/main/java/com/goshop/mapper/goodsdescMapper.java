package com.goshop.mapper;

import com.shop.po.goodsdesc;
import com.shop.po.goodsdescExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface goodsdescMapper {
    int countByExample(goodsdescExample example);

    int deleteByExample(goodsdescExample example);

    int deleteByPrimaryKey(Long goodsId);

    int insert(goodsdesc record);

    int insertSelective(goodsdesc record);

    List<goodsdesc> selectByExample(goodsdescExample example);

    goodsdesc selectByPrimaryKey(Long goodsId);

    int updateByExampleSelective(@Param("record") goodsdesc record, @Param("example") goodsdescExample example);

    int updateByExample(@Param("record") goodsdesc record, @Param("example") goodsdescExample example);

    int updateByPrimaryKeySelective(goodsdesc record);

    int updateByPrimaryKey(goodsdesc record);
}