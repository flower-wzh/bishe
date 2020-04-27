package com.wzh.bishe.dao;

import com.wzh.bishe.entity.User;
import tk.mybatis.mapper.common.Mapper;


/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-30 16:20:00
 */
public interface UserDao extends Mapper<User> {


    String selectIrritability(String userId);
}