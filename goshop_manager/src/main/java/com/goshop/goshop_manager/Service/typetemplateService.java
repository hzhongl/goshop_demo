package com.goshop.goshop_manager.Service;

import com.shop.entity.PageResult;
import com.shop.po.typetemplate;

import java.util.List;
import java.util.Map;

public interface typetemplateService {
    /**
     * 返回全部列表
     * @return
     */
    public List<typetemplate> findAll();


    /**
     * 返回分页列表
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);


    /**
     * 增加
     */
    public void add(typetemplate typetemplate);


    /**
     * 修改
     */
    public void update(typetemplate typetemplate);


    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    public typetemplate findOne(Long id);


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
    public PageResult findPage(typetemplate typetemplate, int pageNum, int pageSize);


    //根据模板ID查询规格列表
    public List<Map> findSpecList(Long id);
}
