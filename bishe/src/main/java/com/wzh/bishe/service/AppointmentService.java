package com.wzh.bishe.service;

import com.wzh.bishe.entity.Appointment;

import java.util.Date;
import java.util.List;

/**
 * (Appointment)表服务接口
 *
 * @author makejava
 * @since 2020-04-06 21:16:25
 */
public interface AppointmentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Appointment queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Appointment> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param appointment 实例对象
     * @return 实例对象
     */
    Appointment insert(Appointment appointment);

    /**
     * 修改数据
     *
     * @param appointment 实例对象
     * @return 实例对象
     */
    Appointment update(Appointment appointment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);


    /**
     * 查询商家某天预约情况
     * @param clinicId 商家Id
     * @param curDate 当前日期
     * @return 返回查询结果
     */
    List<Appointment> findByClinicIdAndDate(String clinicId, Date curDate);

    /**
     * 查询用户所有订单
     * @param userId 用户id
     * @param flag 查询标记（已完成 未完成 全部）
     * @return 用户预约信息
     */
    List<Appointment> findByUserId(String userId ,String flag);

    List<Appointment> findAllAppointment(Integer rows, Integer page ,String clinicId);

    Integer count();
}