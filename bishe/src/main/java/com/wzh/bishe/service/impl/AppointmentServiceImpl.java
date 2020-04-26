package com.wzh.bishe.service.impl;

import com.wzh.bishe.dao.AppointmentDao;
import com.wzh.bishe.entity.Appointment;
import com.wzh.bishe.service.AppointmentService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * (Appointment)表服务实现类
 *
 * @author makejava
 * @since 2020-04-06 21:16:26
 */
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDao appointmentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Appointment queryById(String id) {
        return this.appointmentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Appointment> queryAllByLimit(int offset, int limit) {
        return this.appointmentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param appointment 实例对象
     * @return 实例对象
     */
    @Override
    public Appointment insert(Appointment appointment) {
        appointment.setId(UUID.randomUUID().toString().replace("-",""));
        appointment.setDate(new Date());
        appointment.setStatus("0");
        appointmentDao.insertSelective(appointment);
        return appointment;
    }

    /**
     * 修改数据
     *
     * @param appointment 实例对象
     * @return 实例对象
     */
    @Override
    public Appointment update(Appointment appointment) {
        this.appointmentDao.update(appointment);
        return this.queryById(appointment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.appointmentDao.deleteById(id) > 0;
    }

    @Override
    public List<Appointment> findByClinicIdAndDate(String clinicId, Date curDate) {
//        Example example = new Example(Appointment.class);
//        example.createCriteria().andEqualTo("clinicId",clinicId).andEqualTo("date",curDate);
        return appointmentDao.findByClinicIdAndDate(clinicId);
    }

    @Override
    public List<Appointment> findByUserId(String userId , String flag) {
//        Example example = new Example(Appointment.class);
//        Example.Criteria criteria = example.createCriteria().andEqualTo("userId", userId);
//        if("yes".equals(flag)){
//            criteria.andEqualTo("status","1");
//        }else if("no".equals(flag)){
//            criteria.andEqualTo("status","0");
//        }
        return appointmentDao.findByUserId(userId,flag);
    }

    @Override
    public List<Appointment> findAllAppointment(Integer rows, Integer page) {
        Integer offset = (page-1)*rows;
        return appointmentDao.selectByRowBounds(new Appointment(),new RowBounds(offset,rows));
    }

    @Override
    public Integer count() {
        return appointmentDao.selectCount(new Appointment());
    }
}