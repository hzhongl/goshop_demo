package com.goshop.mapper;

import com.shop.po.provinces;
import com.shop.po.provincesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface provincesMapper {
    int countByExample(provincesExample example);

    int deleteByExample(provincesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(provinces record);

    int insertSelective(provinces record);

    List<provinces> selectByExample(provincesExample example);

    provinces selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") provinces record, @Param("example") provincesExample example);

    int updateByExample(@Param("record") provinces record, @Param("example") provincesExample example);

    int updateByPrimaryKeySelective(provinces record);

    int updateByPrimaryKey(provinces record);
}