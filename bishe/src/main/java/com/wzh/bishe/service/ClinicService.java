package com.wzh.bishe.service;

import com.wzh.bishe.entity.Clinic;

import java.util.List;

/**
 * (Clinic)表服务接口
 *
 * @author makejava
 * @since 2020-03-19 15:14:56
 */
public interface ClinicService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Clinic queryById(String id);

    /**
     * 查询所有
     * @return 返回所有数据
     */
    List<Clinic> queryAllToEs();

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Clinic> queryAllByLimit(Double latitude,Double longitude, int offset, int limit);

    /**
     * 新增数据
     *
     * @param clinic 实例对象
     * @return 实例对象
     */
    Clinic insert(Clinic clinic);

    /**
     * 修改数据
     *
     * @param clinic 实例对象
     * @return 实例对象
     */
    Clinic update(Clinic clinic);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);


    /**
     * 查找一定范围的人
     * @param latitude 用户纬度
     * @param longitude 用户经度
     * @param km 范围
     * @return
     */
    List<Clinic> findByLatLng(Double latitude,Double longitude, Double km);

    Integer count();

    List<Clinic> findAllClinic(Integer rows, Integer page);
}