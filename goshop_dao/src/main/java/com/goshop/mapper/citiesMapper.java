package com.goshop.mapper;

import com.shop.po.cities;
import com.shop.po.citiesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface citiesMapper {
    int countByExample(citiesExample example);

    int deleteByExample(citiesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(cities record);

    int insertSelective(cities record);

    List<cities> selectByExample(citiesExample example);

    cities selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") cities record, @Param("example") citiesExample example);

    int updateByExample(@Param("record") cities record, @Param("example") citiesExample example);

    int updateByPrimaryKeySelective(cities record);

    int updateByPrimaryKey(cities record);
}