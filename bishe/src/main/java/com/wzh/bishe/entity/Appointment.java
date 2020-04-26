package com.wzh.bishe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * (Appointment)实体类
 *
 * @author makejava
 * @since 2020-04-06 21:16:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Appointment implements Serializable {
    private static final long serialVersionUID = -50144259101773337L;

    @Id
    private String id;
    
    private String number;
    
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;
    
    private String userId;
    
    private String clinicId;
    
    private String sign;

    @Transient
    private String phone;
}