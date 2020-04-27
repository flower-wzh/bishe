package com.wzh.bishe.dao;

import com.wzh.bishe.entity.Admin;

public interface AdminDao {
    Admin findPermissionByUserName(String username);

    Admin login(Admin admin);

    void regist(Admin admin);

    void setRole(String id);
}
