package com.web.service;

import com.web.bean.Goods;
import com.web.bean.Order;
import com.web.bean.OrderItem;
import com.web.bean.OrderListAndItems;

import java.util.List;

public interface OrderService {

    List<Order> listByCondition(String condition);

    Integer updateSendStatus(Integer id, Integer status);

    List<OrderItem> listOrderItems(Integer orderid);

    Goods getGoods(Integer integer);

    Integer save(Order order, List<OrderItem> items);

    void updateAlipay(String orderid);

    List<Order> listByUseridAndConditon(Integer id, String condition);

    Order getById(Integer id);

    Order getByOrderid(String orderid);

    List<OrderListAndItems> listOrderListAndItems(Integer id, String condition);

    Integer enterReceive(Integer id);
}
