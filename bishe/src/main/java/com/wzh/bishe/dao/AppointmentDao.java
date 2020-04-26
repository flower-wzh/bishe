package com.wzh.bishe.dao;

import com.wzh.bishe.entity.Appointment;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * (Appointment)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-06 21:16:25
 */
public interface AppointmentDao extends Mapper<Appointment> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Appointment queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Appointment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 修改数据
     *
     * @param appointment 实例对象
     * @return 影响行数
     */
    int update(Appointment appointment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


    /**
     * 查看用户当天预约情况
     * @param clinicId 用户id
     * @return 查询结果
     */
    List<Appointment> findByClinicIdAndDate(String clinicId);

    /**
     * 查询用户所有订单
     * @param userId 用户id
     * @param flag 查询标记（已完成 未完成 全部）
     * @return 用户预约信息
     */
    List<Appointment> findByUserId(@Param("userId") String userId, @Param("flag") String flag);
}