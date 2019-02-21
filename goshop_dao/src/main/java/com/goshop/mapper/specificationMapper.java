package com.goshop.mapper;

import com.shop.po.specification;
import com.shop.po.specificationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface specificationMapper {
    int countByExample(specificationExample example);

    int deleteByExample(specificationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(specification record);

    int insertSelective(specification record);

    List<specification> selectByExample(specificationExample example);

    specification selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") specification record, @Param("example") specificationExample example);

    int updateByExample(@Param("record") specification record, @Param("example") specificationExample example);

    int updateByPrimaryKeySelective(specification record);

    int updateByPrimaryKey(specification record);
    List<Map> selectOptionList();
}