package com.goshop.goshop_manager.Service;

import java.util.List;

import com.shop.entity.PageResult;
import com.shop.po.seller;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SellerService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<seller> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(seller seller);
	
	
	/**
	 * 修改
	 */
	public void update(seller seller);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public seller findOne(String id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(String[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(seller seller, int pageNum, int pageSize);
	
	/**
	 * 更新状态
	 * @param sellerId
	 * @param status
	 */
	public void updateStatus(String sellerId, String status);
	
}
