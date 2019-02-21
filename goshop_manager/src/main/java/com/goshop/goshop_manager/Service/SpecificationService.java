package com.goshop.goshop_manager.Service;

import com.shop.entity.PageResult;
import com.shop.po.brand;
import com.shop.po.specification;
import com.shop.pogroup.customspecifaication;

import java.util.List;
import java.util.Map;

public interface SpecificationService {
    public List<specification> findAll();


    public PageResult findPage(int pageNum, int pageSize);
    /**
     * 增加
     */
    public void add(customspecifaication specification);


    /**
     * 修改
     */
    public void update(customspecifaication specification);


    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    public customspecifaication findOne(Long id);


    /**
     * 批量删除
     * @param ids
     */
    public void delete(Long[] ids);

    /**
     * 分页
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(specification specification, int pageNum,int pageSize);

    public List<Map> selectOptionList();
}
