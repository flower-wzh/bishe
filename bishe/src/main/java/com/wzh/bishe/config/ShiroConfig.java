package com.wzh.bishe.config;


import com.wzh.bishe.filter.AdminFormAuthenticationFilter;
import com.wzh.bishe.realm.AdminRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();//获取filters
        filters.put("authc", new AdminFormAuthenticationFilter());//将自定义 的FormAuthenticationFilter注入shiroFilter
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * 过滤的map
         */
        Map<String,String> map = new LinkedHashMap<>();
        map.put("/back/login.jsp","anon");
        map.put("/back/regist.jsp","anon");
        map.put("/admin/logout","logout");
        map.put("/admin/login", "authc");
        map.put("/back/**","authc");
        map.put("/back/index.jsp","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        /**
         *设置没有登录认证的重定向
         */
        shiroFilterFactoryBean.setLoginUrl("/back/login.jsp");
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        /**
         * 设置没有权限的重定向
         */
        shiroFilterFactoryBean.setUnauthorizedUrl("/all/noAuth.jsp");

        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("adminRealm") AdminRealm adminRealm,
                                                                  @Qualifier("modularRealmAuthenticator")ModularRealmAuthenticator modularRealmAuthenticator) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //securityManager.setRealm(userRealm);
        //认证器多个realm
        //securityManager.setAuthenticator(modularRealmAuthenticator);
        /**
         * 推荐
         */
        securityManager.setRealms(Arrays.asList(adminRealm));
        return securityManager;
    }

    /*
     */
/**
 * 创建Realm
 *//*

    @Bean(name = "userRealm")
    public UserRealm getUserRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }
*/

    /**
     * 创建Realm
     */
    @Bean(name = "adminRealm")
    public AdminRealm getAdminRealm() {
        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCacheManager(new EhCacheManager());
        adminRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return adminRealm;
    }

    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于md5(md5(""));
        //hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);// 表示是否存储散列后的密码为16进制，需要和生成密码时的一样，默认是base64；
        return hashedCredentialsMatcher;
    }

    @Bean(name = "modularRealmAuthenticator")
    public ModularRealmAuthenticator getModularRealmAuthenticator(@Qualifier("adminRealm") AdminRealm adminRealm) {
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        /**
         * 配置realms，可以配置多个源
         * 推荐在securityManager里边配置多个realm
         */
        //modularRealmAuthenticator.setRealms(Arrays.asList(adminRealm));
        /**
         * 更改安全策略为全部通过才可以
         */
        //modularRealmAuthenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

}