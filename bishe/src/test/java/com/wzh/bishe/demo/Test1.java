package com.wzh.bishe.demo;


import com.wzh.bishe.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class Test1 {

    @Test
    public void test(){
        List<Integer> integers = new ArrayList<>();
        String a = "";

        System.out.println(a);

        a = "ss";

        String finalA = a;
        integers.forEach(integer -> {
            new User().setStatus(finalA);
        });
    }
}
