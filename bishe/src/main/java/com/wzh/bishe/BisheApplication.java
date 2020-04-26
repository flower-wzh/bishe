package com.wzh.bishe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wzh.bishe.dao")
public class BisheApplication{

    public static void main(String[] args) {
        SpringApplication.run(BisheApplication.class, args);
    }

}
