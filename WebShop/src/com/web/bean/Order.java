package com.web.bean;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("order")
public class Order {
    private Integer id;
    private String orderid;
    private Double totalPrice;
    private User user;
    private Date createTime;
    private Date sendTime;
    private Integer sendStatus;
    private Integer alipay;
    private Date alipayTime;
    private String sendAddress;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderid='" + orderid + '\'' +
                ", totalPrice=" + totalPrice +
                ", user=" + user +
                ", createTime=" + createTime +
                ", sendTime=" + sendTime +
                ", sendStatus=" + sendStatus +
                ", alipay=" + alipay +
                ", alipayTime=" + alipayTime +
                ", sendAddress='" + sendAddress + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getAlipay() {
        return alipay;
    }

    public void setAlipay(Integer alipay) {
        this.alipay = alipay;
    }

    public Date getAlipayTime() {
        return alipayTime;
    }

    public void setAlipayTime(Date alipayTime) {
        this.alipayTime = alipayTime;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }
}
