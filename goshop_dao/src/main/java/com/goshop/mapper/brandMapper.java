package com.goshop.mapper;

import com.shop.po.brand;
import com.shop.po.brandExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface brandMapper {
    int countByExample(brandExample example);

    int deleteByExample(brandExample example);

    int deleteByPrimaryKey(Long id);

    int insert(brand record);

    int insertSelective(brand record);

    List<brand> selectByExample(brandExample example);

    brand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") brand record, @Param("example") brandExample example);

    int updateByExample(@Param("record") brand record, @Param("example") brandExample example);

    int updateByPrimaryKeySelective(brand record);

    int updateByPrimaryKey(brand record);
    List<Map> selectOptionList();
}