package com.wzh.bishe.service;

import com.wzh.bishe.entity.Comment;

import java.util.List;

/**
 * (Comment)表服务接口
 *
 * @author makejava
 * @since 2020-03-19 15:12:07
 */
public interface CommentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Comment> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @param appointmentId 预约信息id
     * @return 实例对象
     */
    Comment insert(Comment comment,String appointmentId);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 查询一个商家的评论
     * @param clinicId 商家id
     * @return 评论信息
     */
    List<Comment> findByClinicId(String clinicId);

    List<Comment> findAllComments(Integer rows, Integer page,String clinicId);

    Integer count();
}