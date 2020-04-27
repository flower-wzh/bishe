package com.wzh.bishe.filter;

import com.wzh.bishe.entity.Admin;
import com.wzh.bishe.service.AdminService;
import com.wzh.bishe.util.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class AdminFormAuthenticationFilter extends FormAuthenticationFilter {


    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //获取已登录的用户信息
        String username = (String) subject.getPrincipal();
        AdminService adminService = ApplicationContextUtils.getBean(AdminService.class);
        Admin admin = adminService.login(new Admin().setUsername(username));
        //获取session
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpSession session = httpServletRequest.getSession();
        //把用户信息保存到session
        System.out.println(admin);
        session.setAttribute("admin", admin);
        return super.onLoginSuccess(token, subject, request, response);
    }
}
