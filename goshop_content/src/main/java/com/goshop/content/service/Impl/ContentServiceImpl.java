package com.goshop.content.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.goshop.content.service.ContentService;
import com.goshop.mapper.contentMapper;
import com.shop.entity.PageResult;
import com.shop.po.content;
import com.shop.po.contentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private contentMapper contentMapper;

    @Override
    public List<content> findAll() {
        return contentMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<content> page=   (Page<content>) contentMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(content content) {
        contentMapper.insert(content);
        // 清除缓存
        redisTemplate.boundHashOps("content").delete(content.getCategoryId());
    }

    @Override
    public void update(content content) {
        content oldContent = contentMapper.selectByPrimaryKey(content.getId());
        // 清除之前分类的广告缓存
        redisTemplate.boundHashOps("content").delete(oldContent.getCategoryId());

        contentMapper.updateByPrimaryKey(content);
        // 清除缓存
        if(content.getCategoryId() != oldContent.getCategoryId()){
            redisTemplate.boundHashOps("content").delete(content.getCategoryId());
        }
    }

    @Override
    public content findOne(Long id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            content tbContent = contentMapper.selectByPrimaryKey(id);
            redisTemplate.boundHashOps("content").delete(tbContent.getCategoryId());

            contentMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(content content, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        contentExample example=new contentExample();
        contentExample.Criteria criteria = example.createCriteria();

        if(content!=null){
            if(content.getTitle()!=null && content.getTitle().length()>0){
                criteria.andTitleLike("%"+content.getTitle()+"%");
            }
            if(content.getUrl()!=null && content.getUrl().length()>0){
                criteria.andUrlLike("%"+content.getUrl()+"%");
            }
            if(content.getPic()!=null && content.getPic().length()>0){
                criteria.andPicLike("%"+content.getPic()+"%");
            }
            if(content.getStatus()!=null && content.getStatus().length()>0){
                criteria.andStatusLike("%"+content.getStatus()+"%");
            }

        }

        Page<content> page= (Page<content>)contentMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<content> findByCategoryId(Long categoryId) {
     List<content> list = (List<content>) redisTemplate.boundHashOps("content").get(categoryId);

        if(list==null){
            System.out.println("查询数据库===================");
            contentExample example = new contentExample();
            contentExample.Criteria criteria = example.createCriteria();
            // 有效广告:
            criteria.andStatusEqualTo("1");

            criteria.andCategoryIdEqualTo(categoryId);
            // 排序
            example.setOrderByClause("sort_order");

            list = contentMapper.selectByExample(example);

            redisTemplate.boundHashOps("content").put(categoryId, list);
        }else{
            System.out.println("从缓存中获取====================");
        }


        return list;
    }

}
