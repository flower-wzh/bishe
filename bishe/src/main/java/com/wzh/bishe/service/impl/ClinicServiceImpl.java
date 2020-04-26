package com.wzh.bishe.service.impl;

import com.wzh.bishe.dao.ClinicDao;
import com.wzh.bishe.entity.Clinic;
import com.wzh.bishe.service.ClinicService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Clinic)表服务实现类
 *
 * @author makejava
 * @since 2020-03-19 15:14:56
 */
@Service
@Transactional
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    private ClinicDao clinicDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Clinic queryById(String id) {
        return this.clinicDao.queryById(id);
    }

    @Override
    public List<Clinic> queryAllToEs() {
        return clinicDao.queryAllToEs();
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
    public List<Clinic> queryAllByLimit(Double latitude,Double longitude,int offset, int limit) {
        return this.clinicDao.queryAllByLimit(latitude,longitude,offset, limit);
    }

    /**
     * 新增数据
     *
     * @param clinic 实例对象
     * @return 实例对象
     */
    @Override
    public Clinic insert(Clinic clinic) {
        this.clinicDao.insert(clinic);
        return clinic;
    }

    /**
     * 修改数据
     *
     * @param clinic 实例对象
     * @return 实例对象
     */
    @Override
    public Clinic update(Clinic clinic) {
        this.clinicDao.update(clinic);
        return this.queryById(clinic.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.clinicDao.deleteById(id) > 0;
    }

    /**
     * 查找一定范围的商户
     * @param latitude 用户纬度
     * @param longitude 用户经度
     * @param km 范围
     * @return 查询结果
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Clinic> findByLatLng(Double latitude, Double longitude, Double km) {
        return clinicDao.findByLatLng(latitude, longitude, km);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer count() {
        return clinicDao.selectCount(new Clinic());
    }

    @Override
    public List<Clinic> findAllClinic(Integer rows, Integer page) {
        Integer offset = (page-1)*rows;
        return clinicDao.selectByRowBounds(new Clinic(),new RowBounds(offset,rows));
    }
}