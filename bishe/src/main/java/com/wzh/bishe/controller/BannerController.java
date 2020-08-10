package com.wzh.bishe.controller;

import com.wzh.bishe.entity.Banner;
import com.wzh.bishe.service.BannerService;
import com.wzh.bishe.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banner")
@Slf4j
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("findAll")
    public Map<String,Object> findAll(Integer rows, Integer page){
        Map<String,Object> map = new HashMap<>();
        List<Banner> banners = bannerService.findAllBanner(rows,page);
        Integer count = bannerService.count();
        Integer pages = count%rows==0?count/rows:count/rows+1;
        map.put("page",page);
        map.put("records",count);
        map.put("total",pages);
        map.put("rows",banners);
        return map;
    }

    @RequestMapping("/edit")
    public Map<String, Object> editBanner(Banner banner,String oper,String[] id) {
        log.info("controller"+banner+"");
        log.info(oper);
        Map<String, Object> map = new HashMap<>();
        if("add".equals(oper)){
            map = bannerService.addBanner(banner);
        }
        if ("edit".equals(oper)){
            map = bannerService.modifyBanner(banner);
        }
        if ("del".equals(oper)){
            bannerService.removeBanners(id);
        }
        return map;
    }

    @RequestMapping("/upload")
    public void upload(MultipartFile url, String bannerId, HttpServletRequest request) throws UnknownHostException {
        if(StringUtil.isNotEmpty(url.getOriginalFilename())){
            log.info(bannerId);
            String realPath = request.getSession().getServletContext().getRealPath("/file/img/");
            //调用文件上传方法
            String fileName = FileUploadUtil.upload(url, realPath);
            String http = request.getScheme();
            log.info(http);
            String localHost = InetAddress.getLocalHost().toString();
            log.info(localHost);
            int serverPort = request.getServerPort();
            String contextPath = request.getContextPath();
            String uri = http+"://"+localHost.split("/")[1]+":"+serverPort+contextPath+"/file/img/"+fileName;
            Banner banner = new Banner();
            banner.setId(bannerId);
            banner.setUrl(uri);
            log.info(uri);
            bannerService.modifyBanner(banner);
        }
    }
/*    @RequestMapping("/remove")
    public void removeBanner(String id) {
        bannerService.removeBanner(id);
    }
    @RequestMapping("/modify")
    public void modifyBanner(Banner banner) {
        bannerService.modifyBanner(banner);
    }*/

    @RequestMapping("/findOne")
    public Banner findOneBanner(String id) {
        return bannerService.findOneBanner(id);
    }

}
