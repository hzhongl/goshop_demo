package com.goshop.mapper;


import java.util.List;

import com.shop.po.seller;
import com.shop.po.sellerExample;
import org.apache.ibatis.annotations.Param;

public interface sellerMapper {
    int countByExample(sellerExample example);

    int deleteByExample(sellerExample example);

    int deleteByPrimaryKey(String sellerId);

    int insert(seller record);

    int insertSelective(seller record);

    List<seller> selectByExample(sellerExample example);

    seller selectByPrimaryKey(String sellerId);

    int updateByExampleSelective(@Param("record") seller record, @Param("example") sellerExample example);

    int updateByExample(@Param("record") seller record, @Param("example") sellerExample example);

    int updateByPrimaryKeySelective(seller record);

    int updateByPrimaryKey(seller record);
}