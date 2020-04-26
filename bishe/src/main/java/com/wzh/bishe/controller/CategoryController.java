package com.wzh.bishe.controller;

import com.wzh.bishe.entity.Category;
import com.wzh.bishe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (Category)表控制层
 *
 * @author makejava
 * @since 2020-03-30 17:29:17
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Category selectOne(String id) {
        return this.categoryService.queryById(id);
    }


    @RequestMapping("menuList")
    public List<Category> menuList(){
        return categoryService.findForMenu();
    }

    @RequestMapping("/all")
    public List<Category> allCategory(){
        return categoryService.findAllCategory();
    }
}