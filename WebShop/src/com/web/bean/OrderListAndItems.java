package com.web.bean;

public class OrderListAndItems {
    private Order order;
    private OrderItem item;

    @Override
    public String toString() {
        return "OrderListAndItems{" +
                "order=" + order +
                ", item=" + item +
                '}';
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderItem getItem() {
        return item;
    }

    public void setItem(OrderItem item) {
        this.item = item;
    }
}
