package com.goshop.mapper;

import com.shop.po.areas;
import com.shop.po.areasExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface areasMapper {
    int countByExample(areasExample example);

    int deleteByExample(areasExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(areas record);

    int insertSelective(areas record);

    List<areas> selectByExample(areasExample example);

    areas selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") areas record, @Param("example") areasExample example);

    int updateByExample(@Param("record") areas record, @Param("example") areasExample example);

    int updateByPrimaryKeySelective(areas record);

    int updateByPrimaryKey(areas record);
}