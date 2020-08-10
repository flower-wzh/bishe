package com.wzh.bishe.controller;

import com.wzh.bishe.entity.Admin;
import com.wzh.bishe.entity.Clinic;
import com.wzh.bishe.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(Admin admin, HttpSession session) {
        log.info(admin.toString());
        UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(),admin.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            Admin login = (Admin) subject.getPrincipal();
            System.out.println(login);
            session.setAttribute("admin",login);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "登录失败";
        }
        return "success";
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) {
        session.removeAttribute("admin");
    }

    @RequestMapping("regist")
    public String regist(Admin admin, Clinic clinic ,HttpSession session){
        try {
            adminService.regist(admin,clinic);
        } catch (Exception e) {
            e.printStackTrace();
            return "注册失败";
        }
        return "success";
    }
}
