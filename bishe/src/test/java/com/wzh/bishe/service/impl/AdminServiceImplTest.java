package com.wzh.bishe.service.impl;

import com.wzh.bishe.entity.Admin;
import com.wzh.bishe.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

    @Test
    void findPermissionByUsername() {
        Admin admin = adminService.findPermissionByUsername("admin");
        System.out.println(admin);

    }

    @Test
    void login() {
        Admin admin = adminService.login(new Admin().setUsername("admin"));
        System.out.println(admin);
    }
}