package com.wzh.bishe.controller;

import com.wzh.bishe.entity.Appointment;
import com.wzh.bishe.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Appointment)表控制层
 *
 * @author makejava
 * @since 2020-04-06 21:16:26
 */
@RestController
@RequestMapping("appointment")
public class AppointmentController {
    /**
     * 服务对象
     */
    @Autowired
    private AppointmentService appointmentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Appointment selectOne(String id) {
        return this.appointmentService.queryById(id);
    }

    @RequestMapping("/showMy")
    public List<Appointment> findMyYuyue(String userId , String flag){
        //评价完status未-1 表示已经评论过 在插入评论时根据订单号更新赴约记录表
        return appointmentService.findByUserId(userId,flag);
    }

    @RequestMapping("/addAppointment")
    public Map<String , Object> addAppointment(Appointment appointment){
        Map<String,Object> map = new HashMap<>();
        Appointment insert = null;
        try {
            insert = appointmentService.insert(appointment);
            map.put("subject",insert);
            map.put("status",200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("subject",insert);
            map.put("status",-200);
        }
        return map;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Map<String,Object> findAll(Integer rows, Integer page,String clinicId){
        Map<String,Object> map = new HashMap<>();
        List<Appointment> appointmentList = appointmentService.findAllAppointment(rows,page,clinicId);
        Integer count = appointmentService.count();
        Integer pages = count%rows==0?count/rows:count/rows+1;
        map.put("page",page);
        map.put("records",count);
        map.put("total",pages);
        map.put("rows",appointmentList);
        return map;
    }

    @RequestMapping("changeStatus")
    public void changeStatus(Appointment appointment){
        appointment.setStatus("6");
        appointmentService.update(appointment);
    }

}