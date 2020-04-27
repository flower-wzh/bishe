package com.wzh.bishe.controller;

import com.wzh.bishe.entity.Comment;
import com.wzh.bishe.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Comment)表控制层
 *
 * @author makejava
 * @since 2020-03-19 15:12:07
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Comment selectOne(String id) {
        return this.commentService.queryById(id);
    }

    @RequestMapping("addComment")
    public Map<String,Object> addComment(Comment comment,String appointmentId){
        Map<String,Object> map = new HashMap<>();
        Comment insert = null;
        try {
            insert = commentService.insert(comment,appointmentId);
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
    public Map<String,Object> findAll(Integer rows, Integer page,String clinicId){
        Map<String,Object> map = new HashMap<>();
        List<Comment> comments = commentService.findAllComments(rows,page,clinicId);
        Integer count = commentService.count();
        Integer pages = count%rows==0?count/rows:count/rows+1;
        map.put("page",page);
        map.put("records",count);
        map.put("total",pages);
        map.put("rows",comments);
        return map;
    }

}