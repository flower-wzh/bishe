package com.wzh.bishe.service.impl;

import com.wzh.bishe.entity.Comment;
import com.wzh.bishe.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void findByClinicId() {
        List<Comment> byClinicId = commentService.findByClinicId("01");
        byClinicId.forEach(comment -> System.out.println(comment));
    }
}
