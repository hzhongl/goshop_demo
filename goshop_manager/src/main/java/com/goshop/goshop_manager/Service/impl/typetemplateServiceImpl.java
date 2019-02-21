package com.goshop.goshop_manager.Service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.goshop.goshop_manager.Service.typetemplateService;
import com.goshop.mapper.specificationOptionMapper;
import com.goshop.mapper.typetemplateMapper;
import com.shop.entity.PageResult;
import com.shop.po.specificationOption;
import com.shop.po.specificationOptionExample;
import com.shop.po.typetemplate;
import com.shop.po.typetemplateExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class typetemplateServiceImpl implements typetemplateService {
    @Autowired
    private typetemplateMapper typetemplateMapper;
    @Autowired
    private specificationOptionMapper specificationOptionMapper;
    /*查找所有*/
    @Override
    public List<typetemplate> findAll() {
        return typetemplateMapper.selectByExample(null);
    }
        /*分页*/
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<typetemplate> page=  (Page<typetemplate>) typetemplateMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }
    /*增加*/
    @Override
    public void add(typetemplate typetemplate) {
        typetemplateMapper.insert(typetemplate);
    }
    /*修改*/
    @Override
    public void update(typetemplate typetemplate) {
        typetemplateMapper.updateByPrimaryKey(typetemplate);
    }

    @Override
    public typetemplate findOne(Long id) {
        return typetemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id: ids) {
            typetemplateMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(typetemplate typetemplate, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        typetemplateExample example=new typetemplateExample();
        typetemplateExample.Criteria criteria = example.createCriteria();

        if(typetemplate!=null){
            if(typetemplate.getName()!=null && typetemplate.getName().length()>0){
                criteria.andNameLike("%"+typetemplate.getName()+"%");
            }
            if(typetemplate.getSpecIds()!=null && typetemplate.getSpecIds().length()>0){
                criteria.andSpecIdsLike("%"+typetemplate.getSpecIds()+"%");
            }
            if(typetemplate.getBrandIds()!=null && typetemplate.getBrandIds().length()>0){
                criteria.andBrandIdsLike("%"+typetemplate.getBrandIds()+"%");
            }
            if(typetemplate.getCustomAttributeItems()!=null && typetemplate.getCustomAttributeItems().length()>0){
                criteria.andCustomAttributeItemsLike("%"+typetemplate.getCustomAttributeItems()+"%");
            }

        }

        Page<typetemplate> page= (Page<typetemplate>)typetemplateMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Map> findSpecList(Long id) {
        //根据ID查询到模板对象
        typetemplate typeTemplate = typetemplateMapper.selectByPrimaryKey(id);
        // 获得规格的数据spec_ids
        String specIds = typeTemplate.getSpecIds();// [{"id":27,"text":"网络"},{"id":32,"text":"机身内存"}]
        // 将specIds的字符串转成JSON的List<Map>
        List<Map> list = JSON.parseArray(specIds, Map.class);
        // 获得每条记录:
        for (Map map : list) {
            // 根据规格的ID 查询规格选项的数据:
            // 设置查询条件:
            specificationOptionExample example = new specificationOptionExample();
            specificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(new Long((Integer)map.get("id")));

            List<specificationOption> specOptionList = specificationOptionMapper.selectByExample(example);

            map.put("options", specOptionList);//{"id":27,"text":"网络",options:[{id：xxx,optionName:移动2G}]}
        }
        return list;
    }
}
