package com.wzh.bishe.service;

import com.wzh.bishe.entity.Clinic;
import org.elasticsearch.common.geo.GeoPoint;

import java.util.List;
import java.util.Map;

public interface ElasticSearchService {

    /**
     * 商家搜索
     * @param a 距离参数
     * @param lat 用户纬度
     * @param lon 用户经度
     * @param b 类型参数
     * @param c 排序方式
     * @param text 搜索关键字
     * @return 符合条件的所有商家
     */
    List<Clinic> search(String a , String lat , String lon, String b, String c, String text);


    List<Map<String, Object>> searchGeoDistance(String index, String type, String field, String distance, GeoPoint point) throws Exception;


    /**
     * 同步es与数据库的数据
     */
    void createIndex();
}
