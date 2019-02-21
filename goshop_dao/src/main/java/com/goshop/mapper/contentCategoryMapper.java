package com.goshop.mapper;

import com.shop.po.contentCategory;
import com.shop.po.contentCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface contentCategoryMapper {
    int countByExample(contentCategoryExample example);

    int deleteByExample(contentCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(contentCategory record);

    int insertSelective(contentCategory record);

    List<contentCategory> selectByExample(contentCategoryExample example);

    contentCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") contentCategory record, @Param("example") contentCategoryExample example);

    int updateByExample(@Param("record") contentCategory record, @Param("example") contentCategoryExample example);

    int updateByPrimaryKeySelective(contentCategory record);

    int updateByPrimaryKey(contentCategory record);
}