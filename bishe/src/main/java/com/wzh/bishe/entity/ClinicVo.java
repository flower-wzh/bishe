package com.wzh.bishe.entity;

import java.io.Serializable;

public class ClinicVo implements Serializable {
    private String id;
    private String name;
    private String img;
    private String star;
    private String type;
    private String distance;

    public ClinicVo() {
    }

    public ClinicVo(String id, String name, String img, String star, String type, String distance) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.star = star;
        this.type = type;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
