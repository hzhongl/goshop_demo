package com.goshop.goshop_manager.Service;

import com.shop.entity.PageResult;
import com.shop.po.brand;

import java.util.List;
import java.util.Map;

/*
* 品牌接口
*
* */
public interface BrandService {
    /*查找所有品牌*/
    public List<brand> findAll();

    /**
     * 品牌分页
     * @param pageNum 当前页面
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);

    /**
     * 增加
     * @param brand
     */
    public void add(brand brand);

    /**
     * 根据ID查询实体
     * @param id
     * @return
     */
    public brand findOne(Long id);

    /**
     * 修改
     * @param brand
     */
    public void update(brand brand);


    /**
     * 删除
     * @param ids
     */
    public void delete(Long[] ids);
//
//
//	/**
//	 * 品牌分页
//	 * @param pageNum 当前页面
//	 * @param pageSize 每页记录数
//	 * @return
//	 */
//	public PageResult findPage(TbBrand brand, int pageNum,int pageSize);

    /**
     * 返回下拉列表数据
     * @return
     */
    public List<Map> selectOptionList();

}
