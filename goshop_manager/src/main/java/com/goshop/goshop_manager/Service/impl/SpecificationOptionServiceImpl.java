package com.goshop.goshop_manager.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.goshop.goshop_manager.Service.SpecificationOptionService;
import com.goshop.mapper.specificationOptionMapper;
import com.shop.entity.PageResult;
import com.shop.po.specificationOption;
import com.shop.po.specificationOptionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecificationOptionServiceImpl implements SpecificationOptionService {
    @Autowired
    private specificationOptionMapper specificationOptionMapper;
    @Override
    public List<specificationOption> findAll() {
        return specificationOptionMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<specificationOption> page=   (Page<specificationOption>) specificationOptionMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(specificationOption specificationOption) {
        specificationOptionMapper.insert(specificationOption);
    }

    @Override
    public void update(specificationOption specificationOption) {
        specificationOptionMapper.updateByPrimaryKey(specificationOption);
    }

    @Override
    public specificationOption findOne(Long id) {
        return specificationOptionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            specificationOptionMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(specificationOption specificationOption, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        specificationOptionExample example=new specificationOptionExample();
       specificationOptionExample.Criteria  criteria = example.createCriteria();

        if(specificationOption!=null){
            if(specificationOption.getOptionName()!=null && specificationOption.getOptionName().length()>0){
                criteria.andOptionNameLike("%"+specificationOption.getOptionName()+"%");
            }

        }

        Page<specificationOption> page= (Page<specificationOption>)specificationOptionMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
