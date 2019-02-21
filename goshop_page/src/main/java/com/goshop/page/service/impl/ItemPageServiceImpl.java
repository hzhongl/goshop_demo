package com.goshop.page.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goshop.mapper.goodsMapper;
import com.goshop.mapper.goodsdescMapper;
import com.goshop.mapper.itemMapper;
import com.goshop.mapper.itemcatMapper;
import com.goshop.page.service.ItemPageService;
import com.shop.po.goods;
import com.shop.po.goodsdesc;
import com.shop.po.item;
import com.shop.po.itemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

@Service
public class ItemPageServiceImpl implements ItemPageService {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	//@Value("${pagedir}")
	private String pagedir = "C:\\Users\\Administrator\\HBuilderProjects\\item\\";
	
	
	@Autowired
	private goodsMapper goodsMapper;
	
	@Autowired
	private goodsdescMapper goodsDescMapper;
	
	@Autowired
	private itemcatMapper itemCatMapper;
	
	@Autowired
	private itemMapper itemMapper;
	
	@Override
	public boolean genItemHtml(Long goodsId) {
		
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		
		try {
			Template template = configuration.getTemplate("item1.ftl");
			//创建数据模型
			Map dataModel=new HashMap<>();
			//1.商品主表数据
			goods goods = goodsMapper.selectByPrimaryKey(goodsId);
			System.out.println(goods.toString());
			dataModel.put("goods", goods);
			//2.商品扩展表数据
			System.out.println(goodsId);
			goodsdesc goodsDesc = goodsDescMapper.selectByPrimaryKey(goodsId);
			System.out.println(goodsDesc.toString());
			dataModel.put("goodsDesc", goodsDesc);
			//3.读取商品分类
			
			String itemCat1 = itemCatMapper.selectByPrimaryKey(goods.getCategory1Id()).getName();
			String itemCat2 = itemCatMapper.selectByPrimaryKey(goods.getCategory2Id()).getName();
			String itemCat3 = itemCatMapper.selectByPrimaryKey(goods.getCategory3Id()).getName();
			dataModel.put("itemCat1", itemCat1);
			dataModel.put("itemCat2", itemCat2);
			dataModel.put("itemCat3", itemCat3);
			
			//4.读取SKU列表
			itemExample example=new itemExample();
			itemExample.Criteria criteria = example.createCriteria();
			criteria.andGoodsIdEqualTo(goodsId);//SPU ID
			criteria.andStatusEqualTo("1");//状态有效			
			example.setOrderByClause("is_default desc");//按是否默认字段进行降序排序，目的是返回的结果第一条为默认SKU
			
			List<item> itemList = itemMapper.selectByExample(example);
			dataModel.put("itemList", itemList);
			
			Writer out=new FileWriter(pagedir+goodsId+".html");
			
			template.process(dataModel, out);//输出
			out.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		
	}

	@Override
	public boolean deleteItemHtml(Long[] goodsIds) {
		try {
			for(Long goodsId:goodsIds){
				new File(pagedir+goodsId+".html").delete();		
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}

}
