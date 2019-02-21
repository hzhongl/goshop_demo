package com.goshop.goshop_manager.Service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.goshop.goshop_manager.Service.GoodsService;
import com.goshop.mapper.*;
import com.shop.entity.PageResult;
import com.shop.po.*;
import com.shop.pogroup.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private goodsMapper goodsMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<goods> findAll() {
		return goodsMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<goods> page= (Page<goods>) goodsMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Autowired
	private goodsdescMapper goodsDescMapper;
	
	@Autowired
	private itemMapper itemMapper;
	
	@Autowired
	private itemcatMapper itemCatMapper;
	
	@Autowired
	private brandMapper brandMapper;
	
	@Autowired
	private sellerMapper sellerMapper;
	/**
	 * 增加
	 */
	@Override
	public void add(Goods goods) {
		goods.getGoods().setAuditStatus("0"); // 设置审核的状态
		
		goodsMapper.insert(goods.getGoods()); // 插入商品信息
		
		goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
		
		goodsDescMapper.insert(goods.getGoodsDesc()); // 插入商品的扩展信息
		
		setItemList(goods);
	}

	private void setItemList(Goods goods){
		System.out.println("是否启用规格：" + goods.getGoods().getIsEnableSpec());
		if("1".equals(goods.getGoods().getIsEnableSpec())){
			// 启用规格
			// 保存SKU列表的信息:
			for(com.shop.po.item item:goods.getItemList()){
				// 设置SKU的数据：
				// item.setTitle(title);
				String title = goods.getGoods().getGoodsName();
				Map<String,String> map = JSON.parseObject(item.getSpec(), Map.class);
				for (String key : map.keySet()) {
					title+= " "+map.get(key);
				}
				item.setTitle(title);
				
				setValue(goods,item);
				
				itemMapper.insert(item);
			}
		}else{
			// 没有启用规格
			item item = new item();
			
			item.setTitle(goods.getGoods().getGoodsName());
			
			item.setPrice(goods.getGoods().getPrice());
			
			item.setNum(999);
			
			item.setStatus("0");
			
			item.setIsDefault("1");
			item.setSpec("{}");
			
			setValue(goods,item);
			itemMapper.insert(item);
		}
	}

	private void setValue(Goods goods,item item){
		List<Map> imageList = JSON.parseArray(goods.getGoodsDesc().getItemImages(),Map.class);

		if(imageList.size()>0){
			item.setImage((String)imageList.get(0).get("url"));
		}
		
		// 保存三级分类的ID:
		item.setCategoryid(goods.getGoods().getCategory3Id());
		item.setCreateTime(new Date());
		item.setUpdateTime(new Date());
		// 设置商品ID
		System.out.println("----"+goods.getGoods().getId());
		item.setGoodsId(goods.getGoods().getId());
		item.setSellerId(goods.getGoods().getSellerId());
		itemcat itemCat = itemCatMapper.selectByPrimaryKey(goods.getGoods().getCategory3Id());

		item.setCategory(itemCat.getName());

		brand brand = brandMapper.selectByPrimaryKey(goods.getGoods().getBrandId());

		item.setBrand(brand.getName());

		seller seller = sellerMapper.selectByPrimaryKey(goods.getGoods().getSellerId());

		item.setSeller(seller.getNickName());
	}
	
	/**
	 * 修改
	 */
	@Override
	public void update(Goods goods){
		// 修改商品信息
		goods.getGoods().setAuditStatus("0");
		goodsMapper.updateByPrimaryKey(goods.getGoods());
		// 修改商品扩展信息:
		goodsDescMapper.updateByPrimaryKey(goods.getGoodsDesc());
		// 修改SKU信息:
		// 先删除，再保存:
		// 删除SKU的信息:
		itemExample example = new itemExample();
		itemExample.Criteria criteria = example.createCriteria();
		criteria.andGoodsIdEqualTo(goods.getGoods().getId());
		itemMapper.deleteByExample(example);
		// 保存SKU的信息
		setItemList(goods);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Goods findOne(Long id){
		Goods goods = new Goods();
		// 查询商品表的信息
		goods tbGoods = goodsMapper.selectByPrimaryKey(id);
		goods.setGoods(tbGoods);
		// 查询商品扩展表的信息
		goodsdesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(id);
		goods.setGoodsDesc(tbGoodsDesc);
		
		// 查询SKU表的信息:
		itemExample example = new itemExample();
		itemExample.Criteria criteria = example.createCriteria();
		criteria.andGoodsIdEqualTo(id);
		List<item> list = itemMapper.selectByExample(example);
		goods.setItemList(list);
		
		return goods;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
//			goodsMapper.deleteByPrimaryKey(id);
			goods tbGoods = goodsMapper.selectByPrimaryKey(id);
			tbGoods.setIsDelete("1");
			goodsMapper.updateByPrimaryKey(tbGoods);
		}		
	}
	
	
		@Override
	public PageResult findPage(goods goods, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		goodsExample example=new goodsExample();
			goodsExample.Criteria criteria = example.createCriteria();
		
		//criteria.andIsDeleteIsNull();
		
		if(goods!=null){			
					if(goods.getSellerId()!=null && goods.getSellerId().length()>0){
			System.out.println("设置sellerId: " + goods.getSellerId());
			criteria.andSellerIdEqualTo(goods.getSellerId());
		}
		if(goods.getGoodsName()!=null && goods.getGoodsName().length()>0){
			criteria.andGoodsNameLike("%"+goods.getGoodsName()+"%");
		}
		if(goods.getAuditStatus()!=null && goods.getAuditStatus().length()>0){
			criteria.andAuditStatusLike("%"+goods.getAuditStatus()+"%");
		}
		if(goods.getIsMarketable()!=null && goods.getIsMarketable().length()>0){
			criteria.andIsMarketableLike("%"+goods.getIsMarketable()+"%");
		}
		if(goods.getCaption()!=null && goods.getCaption().length()>0){
			criteria.andCaptionLike("%"+goods.getCaption()+"%");
		}
		if(goods.getSmallPic()!=null && goods.getSmallPic().length()>0){
			criteria.andSmallPicLike("%"+goods.getSmallPic()+"%");
		}
		if(goods.getIsEnableSpec()!=null && goods.getIsEnableSpec().length()>0){
			criteria.andIsEnableSpecLike("%"+goods.getIsEnableSpec()+"%");
		}
		if(goods.getIsDelete()!=null && goods.getIsDelete().length()>0){
			criteria.andIsDeleteLike("%"+goods.getIsDelete()+"%");
		}

		}
		
		Page<goods> page= (Page<goods>)goodsMapper.selectByExample(example);
		System.out.println("##### "+page.getResult());
		System.out.println(page.getTotal());
		return new PageResult(page.getTotal(), page.getResult());
	}

		@Override
		public void updateStatus(Long[] ids, String status) {
			for (Long id : ids) {
				goods tbGoods = goodsMapper.selectByPrimaryKey(id);
				System.out.println("----"+tbGoods.getId());
				itemExample example=new itemExample();
				itemExample.Criteria criteria = example.createCriteria();
				criteria.andGoodsIdEqualTo(tbGoods.getId());
				tbGoods.setAuditStatus(status);
				List<item> items= itemMapper.selectByExample(example);
				for (item item:items){
					item.setStatus(status);
					itemMapper.updateByPrimaryKey(item);
				}
				goodsMapper.updateByPrimaryKey(tbGoods);

			}
		}

		@Override
		public List<item> findItemListByGoodsIdListAndStatus(Long[] goodsIds, String status) {
			System.out.println("Goods Id列表：" + goodsIds);
			System.out.println("状态：" + status);
			itemExample example=new itemExample();
			itemExample.Criteria criteria = example.createCriteria();
			criteria.andStatusEqualTo(status);//状态
			System.out.println(Arrays.asList(goodsIds));
			criteria.andGoodsIdIn(Arrays.asList(goodsIds));//指定条件：SPUID集合
			System.out.println(itemMapper.selectByExample(example));
			return itemMapper.selectByExample(example);
		}
	
}
