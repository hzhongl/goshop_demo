package com.goshop.mapper;

import com.shop.po.seckillgoods;
import com.shop.po.seckillgoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface seckillgoodsMapper {
    int countByExample(seckillgoodsExample example);

    int deleteByExample(seckillgoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(seckillgoods record);

    int insertSelective(seckillgoods record);

    List<seckillgoods> selectByExample(seckillgoodsExample example);

    seckillgoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") seckillgoods record, @Param("example") seckillgoodsExample example);

    int updateByExample(@Param("record") seckillgoods record, @Param("example") seckillgoodsExample example);

    int updateByPrimaryKeySelective(seckillgoods record);

    int updateByPrimaryKey(seckillgoods record);
}