package com.goshop.mapper;

import com.shop.po.itemcat;
import com.shop.po.itemcatExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface itemcatMapper {
    int countByExample(itemcatExample example);

    int deleteByExample(itemcatExample example);

    int deleteByPrimaryKey(Long id);

    int insert(itemcat record);

    int insertSelective(itemcat record);

    List<itemcat> selectByExample(itemcatExample example);

    itemcat selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") itemcat record, @Param("example") itemcatExample example);

    int updateByExample(@Param("record") itemcat record, @Param("example") itemcatExample example);

    int updateByPrimaryKeySelective(itemcat record);

    int updateByPrimaryKey(itemcat record);
}