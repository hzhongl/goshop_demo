package com.goshop.goshop_manager.controller;

import com.goshop.goshop_manager.Service.SpecificationService;
import com.shop.entity.PageResult;
import com.shop.entity.Result;
import com.shop.po.specification;
import com.shop.pogroup.customspecifaication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/specification")
@RestController
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;
    @RequestMapping("/findAll")
    public List<specification> findAll(){
        System.out.println("--------");
        return specificationService.findAll();
    }


    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum, int pageSize) {
        return specificationService.findPage(pageNum, pageSize);
    }
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return specificationService.selectOptionList();
    }
    /**
     * 增加
     * @param specification
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody customspecifaication specification){
        try {
            specificationService.add(specification);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     * @param specification
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody customspecifaication specification){
        try {
            specificationService.update(specification);
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
    public customspecifaication findOne(Long id){
        return specificationService.findOne(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            specificationService.delete(ids);
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
    public PageResult search(@RequestBody specification specification, int page, int rows  ){
        System.out.println("###############################################");
        System.out.println("##"+page+"##");
        System.out.println("##"+specification+"##");
        System.out.println("#############################################");
        return specificationService.findPage(specification, page, rows);
    }

}
