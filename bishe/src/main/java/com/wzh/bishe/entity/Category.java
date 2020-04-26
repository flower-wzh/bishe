package com.wzh.bishe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2020-03-30 17:29:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Category implements Serializable {
    private static final long serialVersionUID = 980874248217119563L;

    @Id
    private String id;
    
    private String name;
    
    private String icon;
    
    private String type;
}