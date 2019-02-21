package com.goshop.goshop_search.servie;

import java.util.List;
import java.util.Map;

public interface ItemsearchService {
    /*搜索方法*/
    public Map Search(Map searchMap);
    /*导入列表*/
    public void importList(List list);
    public void importAll();
    /*删除列表*/
    public void deleteByGoodsIds(List goodsIds);
}
