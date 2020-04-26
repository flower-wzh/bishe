package com.wzh.bishe.service.impl;

import com.wzh.bishe.dao.ClinicElasticsearchDao;
import com.wzh.bishe.entity.Clinic;
import com.wzh.bishe.service.ClinicService;
import com.wzh.bishe.service.ElasticSearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
@Slf4j
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private static final String PERSON_INDEX_NAME = "bieshe";
    private static final String PERSON_INDEX_TYPE = "clinic";
    //操作es的对象
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ClinicElasticsearchDao clinicElasticsearchDao;
    @Autowired
    private ClinicService clinicService;

    @Override
    public List<Clinic> search(String a , String lat , String lon , String b, String c, String text) {
        List<Clinic> clinicList = new ArrayList<>();
        //处理数据
        //a为距离
        Integer distance = 20000;
        if(a.equals("1")){
            distance = 500;
        }else if(a.equals("2")){
            distance = 1000;
        }else if(a.equals("3")){
            distance = 2000;
        }else if(a.equals("4")){
            distance = 5000;
        }
        //b为类型
        String type = "";
        if(b.equals("1")){
            type = "儿童";
        }else if (b.equals("2")){
            type = "孕妇";
        }else if (b.equals("3")){
            type = "老人";
        }else if (b.equals("4")){
            type = "西医";
        }else if (b.equals("5")){
            type = "中医";
        }else if (b.equals("6")){
            type = "其他";
        }else if (b.equals("7")){
            type = "中西医结合";
        }else if (b.equals("8")){
            type = "牙科";
        }else if (b.equals("9")){
            type = "口腔科";
        }else if (b.equals("10")){
            type = "眼科";
        }else if (b.equals("11")){
            type = "医疗美容";
        }else if (b.equals("12")){
            type = "精神卫生";
        }else if (b.equals("13")){
            type = "皮肤科";
        }else if (b.equals("14")){
            type = "骨科";
        }else if (b.equals("15")){
            type = "体检";
        }else if (b.equals("16")){
            type = "综合";
        }




        //c为排序方式
        String field = "";
        if(c.equals("1")){
            field = "star";
        }else if(c.equals("2")){
            field = "distance";
        }

        /*GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location")
                .distance(distance, DistanceUnit.METERS)
                .point(new GeoPoint(Double.valueOf(lat), Double.valueOf(lon)));*/

        GeoDistanceQueryBuilder location = QueryBuilders.geoDistanceQuery("location") //查询字段
                .point(Double.valueOf(lat), Double.valueOf(lon))
                .distance(distance, DistanceUnit.METERS)
                .geoDistance(GeoDistance.PLANE);

        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location",Double.valueOf(lat), Double.valueOf(lon))
                .unit(DistanceUnit.METERS)
                .order(SortOrder.ASC)
                .point(Double.valueOf(lat), Double.valueOf(lon))
                .geoDistance(GeoDistance.ARC);

//        NativeSearchQueryBuilder nativeSearchQueryBuilder =
//                new NativeSearchQueryBuilder()
//                        .withQuery(QueryBuilders.multiMatchQuery(text,"name","type"))
//                        .withFilter(location)
//                        .withSort(sort)
//                        .withPageable(PageRequest.of(0,3));
        //Page<Clinic> clinicList = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), Clinic.class);
        System.out.println(text+"-->"+distance+"-->"+type);
        SearchQuery searchQuery = null;
        if(text.equals("请输入搜索内容") && b.equals("0")){
            log.info("text和type为空");
            searchQuery = new NativeSearchQueryBuilder()
                    .withFilter(location)
                    .withPageable(PageRequest.of(0,3))
                    .withSort(sort)
                    .withSort(SortBuilders.fieldSort("star").order(SortOrder.DESC))
                    .build();
        }else if(text.equals("请输入搜索内容")){
            log.info("text为空");
            searchQuery = new NativeSearchQueryBuilder()
                    .withFilter(matchQuery("type", type))
                    .withFilter(location)
                    .withPageable(PageRequest.of(0, 3))
                    .withSort(sort)
                    .withSort(SortBuilders.fieldSort("star").order(SortOrder.DESC))
                    .build();
        }
        else {
            log.info("完整匹配");
            searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(QueryBuilders.multiMatchQuery(text, "name", "type"))
                    .withFilter(matchQuery("type", type))
                    .withFilter(location)
                    .withPageable(PageRequest.of(0, 3))
                    .withSort(sort)
                    .withSort(SortBuilders.fieldSort("star").order(SortOrder.DESC))
                    .build();
        }

        AggregatedPage<Clinic> clinics = elasticsearchTemplate.queryForPage(searchQuery, Clinic.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                List<Clinic> clinicList = new ArrayList<>();
                SearchHit[] hits = searchResponse.getHits().getHits();
                for (SearchHit hit : hits) {
                    Clinic clinic = new Clinic();
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    if(sourceAsMap.get("id")!=null&&!"".equals(sourceAsMap.get("id"))) {
                        clinic.setId(sourceAsMap.get("id").toString());
                    }
                    if(sourceAsMap.get("name")!=null&&!"".equals(sourceAsMap.get("name"))) {
                        clinic.setName(sourceAsMap.get("name").toString());
                    }
                    if(sourceAsMap.get("img")!=null&&!"".equals(sourceAsMap.get("img"))) {
                        clinic.setImg(sourceAsMap.get("img").toString());
                    }
                    if(sourceAsMap.get("type")!=null&&!"".equals(sourceAsMap.get("type"))) {
                        clinic.setType(sourceAsMap.get("type").toString());
                    }
                    if(sourceAsMap.get("star")!=null&&!"".equals(sourceAsMap.get("star"))) {
                        clinic.setStar((Double)sourceAsMap.get("star"));
                    }
                    Object[] sortValues = hit.getSortValues();
                    clinic.setDistance((Double) sortValues[0]);
                    clinicList.add(clinic);
                }
                return new AggregatedPageImpl<T>((List<T>) clinicList);
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });
        clinics.forEach(clinic -> clinicList.add(clinic));
        return clinicList;
    }

    @Override
    public List<Map<String, Object>> searchGeoDistance(String index, String type, String field, String distance, GeoPoint point) throws Exception {
        return null;
    }

//    @Override
//    public List<Map<String, Object>> searchGeoDistance(String index, String type, String field, String distance, GeoPoint point) throws Exception {
//        SearchSourceBuilder builder = QueryFactory.builtDistanceQuery(field,distance,point);
//        return search(index, type, builder);
//    }

//    /**
//     * 执行查询
//     * @param index 索引
//     * @param type type
//     * @param builder 查询语句
//     * @return List<Map>
//     */
//    private List<Map<String, Object>> search(String index, String type, SearchSourceBuilder builder) {
//        try {
//            client = oldclient.setupTransportClient();
//            List<Map<String, Object>> list = new ArrayList<>();
//            SearchRequestBuilder srb = client.prepareSearch(index);
//            if (type != null && type.length() != 0){
//                srb.setTypes(type);
//            }
//            srb.setSource(builder);
//            SearchResponse searchResponse = srb.execute().actionGet();
//            SearchHits hits = searchResponse.getHits();
//            long time = searchResponse.getTookInMillis()/1000;
//            log.info("query result size:"+hits.totalHits+",spend time:"+time+"s");
//            for (SearchHit hit : hits) {
//                Map<String, Object> map = hit.getSource();
//                //获取distance数据时，获取距离具体值
//                if (hit.getSortValues().length != 0){
//                    BigDecimal geoDis = new BigDecimal((Double) hit.getSortValues()[0]);
//                    map.put("距离",geoDis.setScale(4, BigDecimal.ROUND_HALF_DOWN)+"km");
//                }
//                list.add(map);
//                log.info("hits:" + map);
//            }
//            return list;
//        } catch (Exception e) {
//            log.error("error!", e);
//        }
//        return null;
//    }

    @Override
    public void createIndex() {
        clinicElasticsearchDao.saveAll(clinicService.queryAllToEs());
    }
}
