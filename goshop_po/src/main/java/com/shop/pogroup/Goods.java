package com.shop.pogroup;

import com.shop.po.goods;
import com.shop.po.goodsdesc;
import com.shop.po.item;

import java.io.Serializable;
import java.util.List;



/**
 * 商品的组合实体类
 * @author jt
 *
 */

public class Goods implements Serializable{
	
	private goods goods; // 商品信息
	private goodsdesc goodsDesc; // 商品扩展信息
	
	private List<item> itemList; // SKU的列表信息

	public goods getGoods() {
		return goods;
	}

	public void setGoods(goods goods) {
		this.goods = goods;
	}

	public goodsdesc getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(goodsdesc goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public List<item> getItemList() {
		return itemList;
	}

	public void setItemList(List<item> itemList) {
		this.itemList = itemList;
	}
}
