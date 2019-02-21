package com.goshop.mapper;

import com.shop.po.address;
import com.shop.po.addressExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface addressMapper {
    int countByExample(addressExample example);

    int deleteByExample(addressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(address record);

    int insertSelective(address record);

    List<address> selectByExample(addressExample example);

    address selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") address record, @Param("example") addressExample example);

    int updateByExample(@Param("record") address record, @Param("example") addressExample example);

    int updateByPrimaryKeySelective(address record);

    int updateByPrimaryKey(address record);
}