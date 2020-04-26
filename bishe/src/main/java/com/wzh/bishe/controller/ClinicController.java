package com.wzh.bishe.controller;

import com.wzh.bishe.entity.Appointment;
import com.wzh.bishe.entity.Clinic;
import com.wzh.bishe.entity.Comment;
import com.wzh.bishe.service.AppointmentService;
import com.wzh.bishe.service.ClinicService;
import com.wzh.bishe.service.CommentService;
import com.wzh.bishe.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Clinic)表控制层
 *
 * @author makejava
 * @since 2020-03-19 15:14:56
 */
@RestController
@RequestMapping("clinic")
public class ClinicController {
    /**
     * 服务对象
     */
    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private CommentService commentService;
    /**
     * 通过主键查询单条数据
     *
     * @param clinicId 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public Clinic selectOne(String clinicId) {
        return this.clinicService.queryById(clinicId);
    }


    /**
     * 商家搜索
     * @param a 距离参数
     * @param lat 用户纬度
     * @param lon 用户经度
     * @param b 类型参数
     * @param c 排序方式
     * @param text 搜索关键字
     * @return 符合条件的所有商家
     */
    @RequestMapping("/search")
    public List<Clinic> search(String a, String lat , String lon, String b, String c, String text){
        Map<String,Object> map = new HashMap<>();
        System.out.println("a:"+a+",b:"+b+",c:"+c+",lat:"+lat+",lon"+lon+",text:"+text);

        return elasticSearchService.search(a,lat,lon, b, c, text);
    }

    /**
     * 查询当前商家预约信息
     * @param clinicId 商家id
     * @return 返回预约信息结果
     */
    @RequestMapping("/yuyue")
    public List<Appointment> yuyue(String clinicId){
        return appointmentService.findByClinicIdAndDate(clinicId,new Date());
    }

    /**
     * 查询商家评论信息
     * @param clinicId 商家id
     * @return 商家的评论结果列表
     */
    @RequestMapping("/comment")
    public List<Comment> comments(String clinicId){
        return commentService.findByClinicId(clinicId);
    }

    @RequestMapping("/show")
    @ResponseBody
    public Map<String,Object> findAll(Integer rows, Integer page){
        Map<String,Object> map = new HashMap<>();
        List<Clinic> clinicList = clinicService.findAllClinic(rows,page);
        Integer count = clinicService.count();
        Integer pages = count%rows==0?count/rows:count/rows+1;
        map.put("page",page);
        map.put("records",count);
        map.put("total",pages);
        map.put("rows",clinicList);
        return map;
    }
}