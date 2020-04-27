package com.wzh.bishe.service.impl;

import com.wzh.bishe.dao.CommentDao;
import com.wzh.bishe.entity.Appointment;
import com.wzh.bishe.entity.Clinic;
import com.wzh.bishe.entity.Comment;
import com.wzh.bishe.service.AppointmentService;
import com.wzh.bishe.service.ClinicService;
import com.wzh.bishe.service.CommentService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * (Comment)表服务实现类
 *
 * @author makejava
 * @since 2020-03-19 15:12:07
 */
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ClinicService clinicService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Comment queryById(String id) {
        return this.commentDao.queryById(id);
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
    public List<Comment> queryAllByLimit(int offset, int limit) {
        return this.commentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment insert(Comment comment,String appointmentId) {
        comment.setId(UUID.randomUUID().toString().replace("-",""));
        comment.setTime(new Date());
        this.commentDao.insert(comment);

        //计算商家评分
        Double star = commentDao.avgStarCount(comment.getClinicId());
        Clinic update = clinicService.update(new Clinic().setId(comment.getClinicId()).setStar(star));
        //修改预约信息status 为3
        Appointment update1 = appointmentService.update(new Appointment().setId(appointmentId).setStatus("-1"));
        return comment;
    }

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment update(Comment comment) {
        this.commentDao.update(comment);
        return this.queryById(comment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.commentDao.deleteById(id) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Comment> findByClinicId(String clinicId) {
        return commentDao.queryByClinicId(clinicId);
    }

    @Override
    public List<Comment> findAllComments(Integer rows, Integer page ,String clinicId) {
        Integer offset = (page-1)*rows;
        return commentDao.selectByRowBounds(new Comment().setClinicId(clinicId),new RowBounds(offset,rows));
    }

    @Override
    public Integer count() {
        return commentDao.selectCount(new Comment());
    }
}