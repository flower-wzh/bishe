package com.wzh.bishe.controller;


import com.alibaba.fastjson.JSONObject;
import com.wzh.bishe.entity.User;
import com.wzh.bishe.service.UserService;
import com.wzh.bishe.util.AesCbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @RequestMapping("/loginByWX")
    @ResponseBody
    public Map loginByWeixin(String code, String encryptedData, String iv, HttpServletRequest request) {
        Map<String,Object> map  =new HashMap<>();
        System.out.println("encryptedData:"+encryptedData);
        System.out.println("iv:"+iv);
        System.out.println("code:"+code);
        String sendGet=userService.loginByWeixin(code); //根据code去调用接口获取用户openid和session_key
        JSONObject json = JSONObject.parseObject(sendGet);
        //JSONObject json = JSONObject.fromObject(sendGet);
        System.out.println("返回过来的json数据:"+json.toString());
        String sessionkey=json.get("session_key").toString(); //会话秘钥
        String openid=json.get("openid").toString(); //用户唯一标识
        //查看当前用户是否在库中
        User byUserId = userService.findByUserId(openid);
        if(byUserId != null){
            map.put("status","200");
            map.put("subject",byUserId);
        }else{
            //解析用户数据，存入数据库
            try{
                //拿到用户session_key和用户敏感数据进行解密，拿到用户信息。
                String decrypts= AesCbcUtil.decrypt(encryptedData,sessionkey,iv,"utf-8");//解密
                JSONObject jsons = JSONObject.parseObject(decrypts);
                User user = new User();
                user.setId(openid);
                user.setNickName(jsons.get("nickName").toString()); //用户昵称
                user.setAvatarUrl(jsons.get("avatarUrl").toString()); //用户头像
                user.setGender(jsons.get("gender").toString());//性别
                /*if(jsons.get("unionid").toString() != null){
                    user.setUnionId(jsons.get("unionid").toString()); //unionid
                }*/
                user.setCity(jsons.get("city").toString()); //城市
                user.setProvince(jsons.get("province").toString());//省份
                user.setCountry(jsons.get("country").toString()); //国家
                user.setLoginTime(new Date());
                map = userService.addUser(user);
            }catch (Exception e) {
                e.printStackTrace();
                map.put("status","-200");
            }
        }
        return map;
    }

    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(String openid){
        System.out.println(openid);
        Map<String,Object> map  =new HashMap<>();
        //查看当前用户是否在库中
        User byUserId = userService.findByUserId(openid);
        if(byUserId != null) {
            map.put("status", "200");
            map.put("subject", byUserId);
        }else {
            map.put("status","-200");
        }
        return map;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Map<String,Object> findAll(Integer rows, Integer page){
        Map<String,Object> map = new HashMap<>();
        List<User> users = userService.findAllUser(rows,page);
        Integer count = userService.count();
        Integer pages = count%rows==0?count/rows:count/rows+1;
        map.put("page",page);
        map.put("records",count);
        map.put("total",pages);
        map.put("rows",users);
        return map;
    }
}
