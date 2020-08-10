package com.wzh.bishe.service.impl;

import com.wzh.bishe.dao.BannerDao;
import com.wzh.bishe.entity.Banner;
import com.wzh.bishe.service.BannerService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> findAllBanner(Integer rows, Integer page) {
        Integer offset = (page-1)*rows;
        return bannerDao.selectByRowBounds(new Banner(),new RowBounds(offset,rows));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer count() {
        return bannerDao.selectCount(new Banner());
    }

    @Override
    public Map<String,Object> addBanner(Banner banner) {
        Map<String,Object> map = new HashMap<>();
        banner.setId(UUID.randomUUID().toString().replace("-",""));
        bannerDao.insert(banner);
        map.put("bannerId",banner.getId());
        map.put("status",200);
        return map;
    }

    @Override
    public void removeBanner(String id) {
        bannerDao.deleteByPrimaryKey(id);
    }

    @Override
    public void removeBanners(String[] id) {
        bannerDao.deleteByIdList(Arrays.asList(id));
    }

    @Override
    public  Map<String,Object> modifyBanner(Banner banner) {
        Map<String,Object> map = new HashMap<>();
        if(StringUtil.isEmpty(banner.getUrl())){
            banner.setUrl(null);
        }
        bannerDao.updateByPrimaryKeySelective(banner);
        map.put("bannerId",banner.getId());
        map.put("status",200);
        return map;
    }

    @Override
    public Banner findOneBanner(String id) {
        return bannerDao.selectByPrimaryKey(id);
    }
}
