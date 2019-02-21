package com.goshop.mapper;

import com.shop.po.orderitem;
import com.shop.po.orderitemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface orderitemMapper {
    int countByExample(orderitemExample example);

    int deleteByExample(orderitemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(orderitem record);

    int insertSelective(orderitem record);

    List<orderitem> selectByExample(orderitemExample example);

    orderitem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") orderitem record, @Param("example") orderitemExample example);

    int updateByExample(@Param("record") orderitem record, @Param("example") orderitemExample example);

    int updateByPrimaryKeySelective(orderitem record);

    int updateByPrimaryKey(orderitem record);
}