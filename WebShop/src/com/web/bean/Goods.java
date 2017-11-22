package com.web.bean;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Alias("goods")
public class Goods {
    private Integer id;
    private String title;
    private String brandName;
    private String image;
    private String detail;
    private GoodsType goodsType;
    private Double price;
    private Double favoritePrice;
    private Integer repertory;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @Override
    public String toString() {
        return "Goods [id=" + id + ", title=" + title + ", brandName=" + brandName + ", image=" + image + ", detail="
                + detail + ", goodsType=" + goodsType + ", price=" + price + ", favoritePrice=" + favoritePrice
                + ", repertory=" + repertory + ", createDate=" + createDate + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getFavoritePrice() {
        return favoritePrice;
    }

    public void setFavoritePrice(Double favoritePrice) {
        this.favoritePrice = favoritePrice;
    }

    public Integer getRepertory() {
        return repertory;
    }

    public void setRepertory(Integer repertory) {
        this.repertory = repertory;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
