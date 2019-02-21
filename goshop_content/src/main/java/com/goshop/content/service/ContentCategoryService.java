package com.goshop.content.service;

import com.shop.entity.PageResult;
import com.shop.po.contentCategory;

import java.util.List;

public interface ContentCategoryService {
    /**
     * 返回全部列表
     * @return
     */
    public List<contentCategory> findAll();


    /**
     * 返回分页列表
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);


    /**
     * 增加
     */
    public void add(contentCategory contentCategory);


    /**
     * 修改
     */
    public void update(contentCategory contentCategory);


    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    public contentCategory findOne(Long id);


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
    public PageResult findPage(contentCategory contentCategory, int pageNum, int pageSize);

}
