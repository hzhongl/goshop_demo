package com.goshop.mapper;

import com.shop.po.seckillOrder;
import com.shop.po.seckillOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface seckillOrderMapper {
    int countByExample(seckillOrderExample example);

    int deleteByExample(seckillOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(seckillOrder record);

    int insertSelective(seckillOrder record);

    List<seckillOrder> selectByExample(seckillOrderExample example);

    seckillOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") seckillOrder record, @Param("example") seckillOrderExample example);

    int updateByExample(@Param("record") seckillOrder record, @Param("example") seckillOrderExample example);

    int updateByPrimaryKeySelective(seckillOrder record);

    int updateByPrimaryKey(seckillOrder record);
}