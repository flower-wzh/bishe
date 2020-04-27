package com.wzh.bishe.service.impl;

import com.wzh.bishe.dao.UserDao;
import com.wzh.bishe.entity.User;
import com.wzh.bishe.service.UserService;
import com.wzh.bishe.tool.WeChatTool;
import com.wzh.bishe.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findByUserId(String userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public Map<String, Object> addUser(User user) {
        Map<String,Object> map = new HashMap<>();
        if(userDao.insertSelective(user) > 0){
            map.put("status","200");
            map.put("subject",user);
        }else {
            map.put("status","-200");
            map.put("subject",user);
        }
        return map;
    }

    @Override
    public String loginByWeixin(String code) {
        Map<String, Object> map = new HashMap();
        //发送    https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code 获取用户的openid和session_key
        //注意这个是 WeChatTool.wxspAppid 是微信小程序的appid 从微信小程序后台获取 WeChatTool.wxspSecret 这个也一样，我这里是用了常量来进行保存方便多次使用
        String params = "appid=" + WeChatTool.wxspAppid + "&secret=" + WeChatTool.wxspSecret + "&js_code=" + code + "&grant_type=authorization_code";
        String sendGet = UrlUtil.sendGet(WeChatTool.url, params); //发起请求拿到key和openid
        return sendGet;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAllUser(Integer rows, Integer page) {
        Integer offset = (page-1)*rows;
        return userDao.selectByRowBounds(new User(),new RowBounds(offset,rows));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer count() {
        return userDao.selectCount(new User());
    }

    @Override
    public String findIrritability(String userId) {
        return userDao.selectIrritability(userId);
    }

    @Override
    public void updateStatus(User user) {
        if(user.getStatus().equals("1")){
            user.setStatus("0");
        }else {
            user.setStatus("1");
        }
        userDao.updateByPrimaryKeySelective(user);
    }
}
