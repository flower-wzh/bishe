package com.wzh.bishe.dao;

import com.wzh.bishe.entity.Clinic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ClinicElasticsearchDao extends ElasticsearchRepository<Clinic,String> {
}
