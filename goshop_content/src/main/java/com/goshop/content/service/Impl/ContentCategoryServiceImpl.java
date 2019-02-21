package com.goshop.content.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.goshop.content.service.ContentCategoryService;
import com.goshop.content.service.ContentService;
import com.goshop.mapper.contentCategoryMapper;
import com.shop.entity.PageResult;
import com.shop.po.contentCategory;
import com.shop.po.contentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private contentCategoryMapper contentCategoryMapper;

    /**
     * 查询全部
     */
    @Override
    public List<contentCategory> findAll() {
        return contentCategoryMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<contentCategory> page=   (Page<contentCategory>) contentCategoryMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(contentCategory contentCategory) {
        contentCategoryMapper.insert(contentCategory);
    }


    /**
     * 修改
     */
    @Override
    public void update(contentCategory contentCategory){
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
    }

    /**
     * 根据ID获取实体
     * @param id
     * @return
     */
    @Override
    public contentCategory findOne(Long id){
        return contentCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            contentCategoryMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(contentCategory contentCategory, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        contentCategoryExample example=new contentCategoryExample();
        contentCategoryExample.Criteria criteria = example.createCriteria();

        if(contentCategory!=null){
            if(contentCategory.getName()!=null && contentCategory.getName().length()>0){
                criteria.andNameLike("%"+contentCategory.getName()+"%");
            }

        }

        Page<contentCategory> page= (Page<contentCategory>)contentCategoryMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
