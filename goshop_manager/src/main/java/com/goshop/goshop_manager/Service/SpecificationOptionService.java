package com.goshop.goshop_manager.Service;

import com.shop.entity.PageResult;
import com.shop.po.specificationOption;

import java.util.List;

public interface SpecificationOptionService {
    /**
     * 返回全部列表
     * @return
     */
    public List<specificationOption> findAll();


    /**
     * 返回分页列表
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    public void add(specificationOption specificationOption);


    /**
     * 修改
     */
    public void update(specificationOption specificationOption);


    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    public specificationOption findOne(Long id);


    /**
     * 批量删除
     * @param ids
     */
    public void delete(Long [] ids);

    /**
     * 分页
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(specificationOption specificationOption, int pageNum,int pageSize);

}
