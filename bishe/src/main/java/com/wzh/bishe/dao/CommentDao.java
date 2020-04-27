package com.wzh.bishe.dao;

import com.wzh.bishe.entity.Comment;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-19 15:12:06
 */
public interface CommentDao extends Mapper<Comment> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Comment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param comment 实例对象
     * @return 对象列表
     */
    List<Comment> queryAll(Comment comment);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    /**
     * 查询一个商家的评论
     * @param clinicId 商家id
     * @return 评论信息
     */
    List<Comment> queryByClinicId(String clinicId);

    /**
     * 计算商家评分
     * @param clinicId 商家id
     * @return 商家平均评分
     */
    Double avgStarCount(String clinicId);
}