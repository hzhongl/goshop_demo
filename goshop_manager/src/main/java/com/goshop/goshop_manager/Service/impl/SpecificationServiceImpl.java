package com.goshop.goshop_manager.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.goshop.goshop_manager.Service.SpecificationService;
import com.goshop.mapper.specificationMapper;
import com.goshop.mapper.specificationOptionMapper;
import com.shop.entity.PageResult;
import com.shop.po.*;
import com.shop.pogroup.customspecifaication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService{
    @Autowired
    private specificationMapper specificationMapper;
    @Autowired
    private  specificationOptionMapper specificationOptionMapper;
    /*查询全部*/
    @Override
    public List<specification> findAll() {
        return specificationMapper.selectByExample(null);
    }
    /*分页*/
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<specification> page= (Page<specification>) specificationMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(customspecifaication specification) {
         specificationMapper.insert(specification.getSpecification());
        for(specificationOption specificationOption: specification.getSpecificationOptionList()){
            // 设置规格的ID:
            specificationOption.setSpecId(specification.getSpecification().getId());

            specificationOptionMapper.insert(specificationOption);
        }
    }

    @Override
    public void update(customspecifaication specification) {
        specificationMapper.updateByPrimaryKey(specification.getSpecification());

        // 先删除规格选项，再添加规格选项
        specificationOptionExample example = new specificationOptionExample();
        specificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());
        specificationOptionMapper.deleteByExample(example);

        // 保存规格选项
        for(specificationOption specificationOption: specification.getSpecificationOptionList()){
            // 设置规格的ID:
            specificationOption.setSpecId(specification.getSpecification().getId());

            specificationOptionMapper.insert(specificationOption);
        }
    }

    @Override
    public customspecifaication findOne(Long id) {
        customspecifaication customspecifaication = new customspecifaication();
        specification specification = specificationMapper.selectByPrimaryKey(id);
        customspecifaication.setSpecification(specification);
        specificationOptionExample example = new specificationOptionExample();
        specificationOptionExample.Criteria  criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<specificationOption> list = specificationOptionMapper.selectByExample(example);
        customspecifaication.setSpecificationOptionList(list);
        return customspecifaication;
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            // 删除规格
            specificationMapper.deleteByPrimaryKey(id);

            // 删除规格选项:
            specificationOptionExample example = new specificationOptionExample();
            specificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
        }
    }

    @Override
    public PageResult findPage(specification specification, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        specificationExample example=new specificationExample();
        specificationExample.Criteria criteria = example.createCriteria();

        if(specification!=null){
            if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
                criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
            }

        }

        Page<specification> page= (Page<specification>)specificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }


    @Override
    public List<Map> selectOptionList() {
        return specificationMapper.selectOptionList();
    }
}
