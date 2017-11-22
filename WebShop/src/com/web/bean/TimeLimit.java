package com.web.bean;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Alias("time")
public class TimeLimit {
    private Integer id;
    private Goods goods;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date limitDate;
    private Integer isEnd;

    @Override
    public String toString() {
        return "TimeLimit [id=" + id + ", goods=" + goods + ", limitDate=" + limitDate + ", isEnd=" + isEnd + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public Integer getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

}
