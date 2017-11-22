package com.web.bean;

import org.apache.ibatis.type.Alias;

@Alias("picture")
public class Picture {
    private Integer id;
    private String imageURL;
    private Goods goods;

    @Override
    public String toString() {
        return "Picture [id=" + id + ", imageURL=" + imageURL + ", goods=" + goods + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

}
