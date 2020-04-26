package com.wzh.bishe.service;

import com.wzh.bishe.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查找用户是否存在
     * @param userId
     * @return
     */
    User findByUserId(String userId);

    Map<String,Object> addUser(User user);

    String loginByWeixin(String code);

    List<User> findAllUser(Integer rows, Integer page);

    Integer count();
}
