package com.goshop.goshop_search.controller;

import com.goshop.goshop_search.servie.ItemsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/itemsearch")
public class ItemSearchController {
    @Autowired
    private ItemsearchService itemSearchService;

    @RequestMapping("/search")
    public Map search(@RequestBody Map searchMap){
        return itemSearchService.Search(searchMap);

    }
    @RequestMapping("/import")
    public String importAll() {
        itemSearchService.importAll();
        return "导入成功";
    }
}
