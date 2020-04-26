package com.wzh.bishe.service.impl;

import com.wzh.bishe.dao.CategoryDao;
import com.wzh.bishe.entity.Category;
import com.wzh.bishe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2020-03-30 17:29:16
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> findForMenu() {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("type","0");
        return categoryDao.selectByExample(example);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Category queryById(String id) {
        return this.categoryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryAllByLimit(int offset, int limit) {
        return this.categoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category insert(Category category) {
        this.categoryDao.insert(category);
        return category;
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category update(Category category) {
        this.categoryDao.update(category);
        return this.queryById(category.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.categoryDao.deleteById(id) > 0;
    }

    @Override
    public List<Category> findAllCategory() {
        Example example = new Example(Category.class);
        example.createCriteria().andNotEqualTo("id","06");
        return categoryDao.selectByExample(example);
    }
}