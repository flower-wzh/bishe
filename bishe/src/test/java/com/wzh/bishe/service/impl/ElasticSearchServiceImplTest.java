package com.wzh.bishe.service.impl;

import com.wzh.bishe.entity.Clinic;
import com.wzh.bishe.service.ElasticSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ElasticSearchServiceImplTest {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Test
    public void search(){
        List<Clinic> clinicList = elasticSearchService.search("0", "34.576556", "113.862104", "7", "0", "请输入搜索内容");
        clinicList.forEach(clinic -> System.out.println(clinic));
    }

    @Test
    public void createIndex() {
        elasticSearchService.createIndex();
    }
}
