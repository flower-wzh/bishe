package com.wzh.bishe.util;

import com.wzh.bishe.service.AdminService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static AdminService getBean(String id){
        AdminService bean = (AdminService) context.getBean(id);
        return bean;
    }

    public static AdminService getBean(Class clazz){
        AdminService bean = (AdminService) context.getBean(clazz);
        return bean;
    }

    public static AdminService getBean(String id ,Class clazz){
        AdminService bean = (AdminService) context.getBean(id,clazz);
        return bean;
    }
}
