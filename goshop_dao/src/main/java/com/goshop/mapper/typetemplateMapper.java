package com.goshop.mapper;

import com.shop.po.typetemplate;
import com.shop.po.typetemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface typetemplateMapper {
    int countByExample(typetemplateExample example);

    int deleteByExample(typetemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(typetemplate record);

    int insertSelective(typetemplate record);

    List<typetemplate> selectByExample(typetemplateExample example);

    typetemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") typetemplate record, @Param("example") typetemplateExample example);

    int updateByExample(@Param("record") typetemplate record, @Param("example") typetemplateExample example);

    int updateByPrimaryKeySelective(typetemplate record);

    int updateByPrimaryKey(typetemplate record);
}