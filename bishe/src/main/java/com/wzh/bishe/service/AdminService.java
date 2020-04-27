package com.wzh.bishe.service;

import com.wzh.bishe.entity.Admin;
import com.wzh.bishe.entity.Clinic;

public interface AdminService {
    /**
     * 查找权限
     * @param toString
     * @return 权限信息
     */
    Admin findPermissionByUsername(String toString);

    /**
     * 管理员登陆
     * @param admin 管理员账号密码信息
     * @return 管理员信息
     */
    Admin login(Admin admin);

    void regist(Admin admin, Clinic clinic);
}
