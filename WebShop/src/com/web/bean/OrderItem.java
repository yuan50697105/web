package com.web.bean;

import org.apache.ibatis.type.Alias;

@Alias("orderitem")
public class OrderItem {
    private Integer id;
    private Goods goods;
    private Integer sum;
    private Integer orderid;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", goods=" + goods +
                ", sum=" + sum +
                ", orderid=" + orderid +
                '}';
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

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
}
