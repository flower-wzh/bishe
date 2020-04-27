package com.wzh.bishe.realm;


import com.wzh.bishe.entity.Admin;
import com.wzh.bishe.service.AdminService;
import com.wzh.bishe.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    /**
     * 授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        /**
         * 从数据库获取当前对象信息
         */
        /**
         * 拿到当前admin对象去数据库五表连查权限
         */
        Admin permissionByUsername = adminService.findPermissionByUsername(primaryPrincipal.toString());
        /**
         * 添加角色权限
         */
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        permissionByUsername.getRoles().forEach(role -> {
            roles.add(role.getName());
            role.getPermissions().forEach(permission -> permissions.add(permission.getName()));
        });
        simpleAuthorizationInfo.addRoles(roles);
        /**
         * 通过数据库查到进行授权认证
         */
        simpleAuthorizationInfo.addStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("UserRealm--->doGetAuthenticationInfo--->认证逻辑");
        //判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        Admin login = adminService.login(new Admin().setUsername(username));
        System.out.println("从数据库获取username----->"+username);
        if (login == null ) {
            return null;
        }
        /**
         * salt
         */
        ByteSource bytes = ByteSource.Util.bytes(login.getSalt());
        System.out.println(login.getPassword());

        return new SimpleAuthenticationInfo(username,login.getPassword(),bytes,getName());
    }

    public static void main(String[] args) {
        String algorithmName = "MD5";
        Object source = "kbj";
        String salt1 = MD5Util.getSalt(16);
        Object salt = ByteSource.Util.bytes(salt1);;
        int hashIterations = 1024;
        SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
        System.out.println(salt1);
        System.out.println(simpleHash);
        //new SimpleAuthenticationInfo( principal,  hashedCredentials, credentialsSalt, realmName);
    }

}
