package com.wzh.bishe.service.impl;

import com.wzh.bishe.dao.AdminDao;
import com.wzh.bishe.entity.Admin;
import com.wzh.bishe.entity.Clinic;
import com.wzh.bishe.service.AdminService;
import com.wzh.bishe.service.ClinicService;
import com.wzh.bishe.util.MD5Util;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private ClinicService clinicService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin findPermissionByUsername(String username) {
        return adminDao.findPermissionByUserName(username);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin login(Admin admin) {
        return adminDao.login(admin);
    }

    @Override
    public void regist(Admin admin, Clinic clinic) {
        String clinicId = UUID.randomUUID().toString().replace("-","");
        String algorithmName = "MD5";
        String salt1 = MD5Util.getSalt(16);
        Object salt = ByteSource.Util.bytes(salt1);;
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(algorithmName, admin.getPassword(), salt, hashIterations);
        admin.setSalt(salt1);
        admin.setPassword(simpleHash.toString());
        admin.setClinicId(clinicId);
        String id = UUID.randomUUID().toString().replace("-", "");
        admin.setId(id);
        adminDao.regist(admin);
        //赋予权限
        adminDao.setRole(id);
        //注册诊所基本信息
        clinicService.addClinic(clinic.setId(clinicId));
    }
}
