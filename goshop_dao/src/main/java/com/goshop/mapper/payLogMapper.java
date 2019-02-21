package com.goshop.mapper;

import com.shop.po.payLog;
import com.shop.po.payLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface payLogMapper {
    int countByExample(payLogExample example);

    int deleteByExample(payLogExample example);

    int deleteByPrimaryKey(String outTradeNo);

    int insert(payLog record);

    int insertSelective(payLog record);

    List<payLog> selectByExample(payLogExample example);

    payLog selectByPrimaryKey(String outTradeNo);

    int updateByExampleSelective(@Param("record") payLog record, @Param("example") payLogExample example);

    int updateByExample(@Param("record") payLog record, @Param("example") payLogExample example);

    int updateByPrimaryKeySelective(payLog record);

    int updateByPrimaryKey(payLog record);
}