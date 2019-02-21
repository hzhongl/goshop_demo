package com.goshop.mapper;

import com.shop.po.content;
import com.shop.po.contentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface contentMapper {
    int countByExample(contentExample example);

    int deleteByExample(contentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(content record);

    int insertSelective(content record);

    List<content> selectByExample(contentExample example);

    content selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") content record, @Param("example") contentExample example);

    int updateByExample(@Param("record") content record, @Param("example") contentExample example);

    int updateByPrimaryKeySelective(content record);

    int updateByPrimaryKey(content record);
}