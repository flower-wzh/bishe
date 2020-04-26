package com.wzh.bishe.service;

import com.wzh.bishe.entity.Category;
import java.util.List;

/**
 * (Category)表服务接口
 *
 * @author makejava
 * @since 2020-03-30 17:29:16
 */
public interface CategoryService {

    List<Category> findForMenu();


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Category queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Category> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category insert(Category category);

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    Category update(Category category);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);


    /**
     * 查询所有分类
     * @return 返回所有类别
     */
    List<Category> findAllCategory();
}