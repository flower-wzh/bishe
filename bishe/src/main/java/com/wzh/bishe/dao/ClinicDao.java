package com.wzh.bishe.dao;

import com.wzh.bishe.entity.Clinic;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * (Clinic)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-19 15:14:56
 */
public interface ClinicDao extends Mapper<Clinic> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Clinic queryById(String id);

    /**
     * 查询指定行数据
     * @param latitude 用户纬度
     * @param longitude 用户经度
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Clinic> queryAllByLimit(@Param("latitude")Double latitude,
                                 @Param("longitude")Double longitude,
                                 @Param("offset") int offset,
                                 @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<Clinic> queryAllToEs();

    /**
     * 新增数据
     *
     * @param clinic 实例对象
     * @return 影响行数
     */
    int insert(Clinic clinic);

    /**
     * 修改数据
     *
     * @param clinic 实例对象
     * @return 影响行数
     */
    int update(Clinic clinic);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


    /**
     * 查找一定距离内的商家
     * @param latitude 用户纬度
     * @param longitude 用户经度
     * @param km 查找范围
     * @return
     */
    List<Clinic> findByLatLng(@Param("latitude") Double latitude,
                              @Param("longitude") Double longitude,
                              @Param("km") Double km);

}