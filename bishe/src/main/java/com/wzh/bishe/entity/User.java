package com.wzh.bishe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-03-30 16:19:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -63363621175460696L;

    @Id
    private String id;
    
    private String nickName;
    
    private String avatarUrl;
    
    private String gender;
    
    private String unionId;
    
    private String city;
    
    private String province;
    
    private String country;
    
    private Date loginTime;


}