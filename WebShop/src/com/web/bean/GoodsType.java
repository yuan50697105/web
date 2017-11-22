package com.web.bean;

import org.apache.ibatis.type.Alias;

@Alias("goodsType")
public class GoodsType {
    private Integer id;
    private String name;
    private String detail;
    private Integer fatherid;

    @Override
    public String toString() {
        return "GoodsType [id=" + id + ", name=" + name + ", detail=" + detail + ", fatherid=" + fatherid + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getFatherid() {
        return fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

}
