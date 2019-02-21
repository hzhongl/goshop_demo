package com.goshop.goshop_manager.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.goshop.goshop_manager.Service.ItemCatService;
import com.goshop.mapper.itemcatMapper;
import com.shop.entity.PageResult;
import com.shop.po.itemcat;
import com.shop.po.itemcatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private itemcatMapper itemCatMapper;

    /**
     * 查询全部
     */
    @Override
    public List<itemcat> findAll() {
        return itemCatMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<itemcat> page=   (Page<itemcat>) itemCatMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(itemcat itemCat) {
        itemCatMapper.insert(itemCat);
    }


    /**
     * 修改
     */
    @Override
    public void update(itemcat itemCat){
        itemCatMapper.updateByPrimaryKey(itemCat);
    }

    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    @Override
    public itemcat findOne(Long id){
        return itemCatMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            itemCatMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(itemcat itemCat, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        itemcatExample example=new itemcatExample();
        itemcatExample.Criteria criteria = example.createCriteria();

        if(itemCat!=null){
            if(itemCat.getName()!=null && itemCat.getName().length()>0){
                criteria.andNameLike("%"+itemCat.getName()+"%");
            }

        }

        Page<itemcat> page= (Page<itemcat>)itemCatMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<itemcat> findByParentId(Long parentId) {
        itemcatExample example = new itemcatExample();
        itemcatExample.Criteria criteria = example.createCriteria();
        // 设置条件:
        criteria.andParentIdEqualTo(parentId);
        // 条件查询
        return itemCatMapper.selectByExample(example);
    }
}
