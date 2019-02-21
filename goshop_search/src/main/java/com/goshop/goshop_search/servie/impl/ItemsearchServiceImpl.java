package com.goshop.goshop_search.servie.impl;


import com.goshop.goshop_search.servie.ItemsearchService;
import com.goshop.mapper.itemMapper;
import com.shop.po.item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ItemsearchServiceImpl implements ItemsearchService{
    @Autowired  // SolrTemplate是默认创建的bean,直接引用即可
    private SolrTemplate solrTemplate;
    @Autowired
    private itemMapper itemMapper;

    @Override
    public Map Search(Map searchMap) {
        Map map=new HashMap();

        Query query=new SimpleQuery("*:*");
        //item_keywords是solr的业务字段
        Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);

        ScoredPage<item> page = solrTemplate.queryForPage("collection1",query, item.class);

        map.put("rows", page.getContent());

        return map;
    }


    @Override
    public void importList(List list) {
        solrTemplate.saveBeans("collection1", list);
        solrTemplate.commit("collection1");
    }

    @Override
    public void importAll() {
        List list = itemMapper.selectByExample(null);
        solrTemplate.saveBeans("collection1", list);
       solrTemplate.commit("collection1");
    }


    @Override
    public void deleteByGoodsIds(List goodsIds) {

        Query query=new SimpleQuery("*:*");
        Criteria criteria=new Criteria("item_goodsid").in(goodsIds);
        query.addCriteria(criteria);
        solrTemplate.delete("collection1",query);
        solrTemplate.commit("collection1");
    }
}
