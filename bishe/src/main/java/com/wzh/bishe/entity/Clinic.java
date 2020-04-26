package com.wzh.bishe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * (Clinic)实体类
 *
 * @author makejava
 * @since 2020-03-19 15:14:56
 */
@Document(indexName = "bishe",type = "clinic")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Clinic implements Serializable {
    private static final long serialVersionUID = -64304507209951023L;

    @Id
    @org.springframework.data.annotation.Id
    private String id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String name;
    
    private String img;

    @Transient
    private String city;

    @Transient
    private String province;

    @Transient
    private String country;

    @Transient
    private String phone;
    
    private Double star;

    private Double latitude;

    private Double longitude;

    @Transient
    @javax.persistence.Transient
    private Double distance;

    @GeoPointField
    private String location;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    @javax.persistence.Transient
    private String type;

    private String address;

}