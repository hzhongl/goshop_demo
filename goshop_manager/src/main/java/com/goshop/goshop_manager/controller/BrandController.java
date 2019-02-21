package com.goshop.goshop_manager.controller;


import com.goshop.goshop_manager.Service.BrandService;
import com.shop.entity.PageResult;
import com.shop.entity.Result;
import com.shop.po.brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/brand")
@RestController
public class BrandController {
    @Autowired
    private BrandService brandService;
    @RequestMapping("/findAll")
    public List<brand> findAll(){
        System.out.println("--------");
        return brandService.findAll();
    }
    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum, int pageSize) {
        return brandService.findPage(pageNum, pageSize);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody brand brand) {
        try {
            brandService.add(brand);
            return new Result(true, "成功添加品牌信息");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加品牌信息失败");
        }
    }

    @RequestMapping("/findOne")
    public brand findBrandDetail(Long id) {
        return brandService.findOne(id);
    }

    @RequestMapping("/updateBrand")
    public Result updateBrand(@RequestBody brand brand) {
        try {
            brandService.update(brand);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(Long [] ids){
        try {
            brandService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() {
        return brandService.selectOptionList();
    }

}
