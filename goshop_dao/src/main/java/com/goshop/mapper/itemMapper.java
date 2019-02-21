package com.goshop.mapper;

import com.shop.po.item;
import com.shop.po.itemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface itemMapper {
    int countByExample(itemExample example);

    int deleteByExample(itemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(item record);

    int insertSelective(item record);

    List<item> selectByExample(itemExample example);

    item selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") item record, @Param("example") itemExample example);

    int updateByExample(@Param("record") item record, @Param("example") itemExample example);

    int updateByPrimaryKeySelective(item record);

    int updateByPrimaryKey(item record);
}