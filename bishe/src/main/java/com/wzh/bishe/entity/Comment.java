package com.wzh.bishe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2020-03-19 15:12:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Comment implements Serializable {
    private static final long serialVersionUID = -23257074885271480L;

    @Id
    private String id;
    
    private String content;
    
    private String image;
    
    private String star;
    
    private Date time;
    
    private String userId;
    
    private String clinicId;

    private User user;
}