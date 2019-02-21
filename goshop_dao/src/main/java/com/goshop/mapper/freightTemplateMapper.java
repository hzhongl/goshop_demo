package com.goshop.mapper;

import com.shop.po.freightTemplate;
import com.shop.po.freightTemplateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface freightTemplateMapper {
    int countByExample(freightTemplateExample example);

    int deleteByExample(freightTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(freightTemplate record);

    int insertSelective(freightTemplate record);

    List<freightTemplate> selectByExample(freightTemplateExample example);

    freightTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") freightTemplate record, @Param("example") freightTemplateExample example);

    int updateByExample(@Param("record") freightTemplate record, @Param("example") freightTemplateExample example);

    int updateByPrimaryKeySelective(freightTemplate record);

    int updateByPrimaryKey(freightTemplate record);
}