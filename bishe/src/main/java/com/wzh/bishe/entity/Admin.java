package com.wzh.bishe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Admin implements Serializable {

    private String id;
    private String username;
    private String password;
    private String clinicId;
    private String salt;
    private List<Role> roles;
}
