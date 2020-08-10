package com.wzh.bishe.service;

import com.wzh.bishe.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {

    /**
     * jqGrid展示表格
     * @param rows 每页数据条数
     * @param page 页数
     * @return banner数据
     */
    List<Banner> findAllBanner(Integer rows, Integer page);

    /**
     * 总banner数据条数
     * @return int数值
     */
    Integer count();

    /**
     * 添加轮播图
     * @param banner 添加的数据
     * @return 添加结果
     */
    Map<String,Object> addBanner(Banner banner);

    /**
     * 删除轮播图
     * @param id 删除数据的id
     */
    void removeBanner(String id);

    /**
     * 批量删除
     * @param id 要删除的数据的id数组
     */
    void removeBanners(String[] id);

    /**
     * 更新轮播图
     * @param banner 更新的轮播图的信息
     */
    Map<String,Object> modifyBanner(Banner banner);

    /**
     * 根据id查找一个Banner详情
     * @param id banner的id
     */
    Banner findOneBanner(String id);

}
