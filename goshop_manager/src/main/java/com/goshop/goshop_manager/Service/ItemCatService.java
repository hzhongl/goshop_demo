package com.goshop.goshop_manager.Service;

import com.shop.entity.PageResult;
import com.shop.po.itemcat;

import java.util.List;

public interface ItemCatService {

    /**
     * 返回全部列表
     * @return
     */
    public List<itemcat> findAll();


    /**
     * 返回分页列表
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);


    /**
     * 增加
     */
    public void add(itemcat itemCat);


    /**
     * 修改
     */
    public void update(itemcat itemCat);


    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    public itemcat findOne(Long id);


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
    public PageResult findPage(itemcat itemCat, int pageNum, int pageSize);

    /**
     * 根据父ID查询分类的方法
     * @param parentId
     * @return
     */
    public List<itemcat> findByParentId(Long parentId);
}
