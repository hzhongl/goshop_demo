package com.goshop.goshop_manager.controller;

import com.goshop.goshop_manager.Service.typetemplateService;
import com.shop.entity.PageResult;
import com.shop.entity.Result;
import com.shop.po.typetemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/typeTemplate")
@RestController
public class typetemplateController {
    @Autowired
    private typetemplateService typeTemplateService;

    /**
     * 返回全部列表
     * @return
     */
    @RequestMapping("/findAll")
    public List<typetemplate> findAll(){
        return typeTemplateService.findAll();
    }
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows){
        return typeTemplateService.findPage(page, rows);
    }
    /**
     * 增加
     * @param typeTemplate
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody typetemplate typeTemplate){
        System.out.println("============================================");
        System.out.println("====================="+typeTemplate.getName()+"====================");
        System.out.println("============================================");
        try {
            typeTemplateService.add(typeTemplate);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     * @param typeTemplate
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody typetemplate typeTemplate){
        try {
            typeTemplateService.update(typeTemplate);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public typetemplate findOne(Long id){
        return typeTemplateService.findOne(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            typeTemplateService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     * @param
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody typetemplate typeTemplate, int page, int rows  ){
        return typeTemplateService.findPage(typeTemplate, page, rows);
    }

    @RequestMapping("/findBySpecList")
    public List<Map> findSpecList(Long id){
        return typeTemplateService.findSpecList(id);
    }
}
