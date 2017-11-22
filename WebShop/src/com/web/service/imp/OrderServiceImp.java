package com.web.service.imp;

import com.web.bean.Goods;
import com.web.bean.Order;
import com.web.bean.OrderItem;
import com.web.bean.OrderListAndItems;
import com.web.mapper.GoodsMapper;
import com.web.mapper.OrderItemMapper;
import com.web.mapper.OrderMapper;
import com.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Order> listByCondition(String condition) {
        return StringUtils.isEmpty(condition) ? orderMapper.listAll() : orderMapper.listByCondition(condition);
    }

    @Override
    public Integer updateSendStatus(Integer id, Integer status) {
        return orderMapper.updateSendStatus(id, status);
    }

    @Override
    public List<OrderItem> listOrderItems(Integer orderid) {
        return orderItemMapper.listByOrderid(orderid);
    }

    @Override
    public Goods getGoods(Integer integer) {
        return goodsMapper.getById(integer);
    }

    @Override
    public Integer save(Order order, List<OrderItem> items) {
        Integer rows = orderMapper.save(order);
        Integer total = 0;
        if (rows > 0) {

            for (OrderItem orderItem : items) {
                orderItem.setOrderid(order.getId());
                rows = orderItemMapper.save(orderItem);
                total += rows;
            }
            if (total == items.size()) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public void updateAlipay(String orderid) {
        orderMapper.updateAlipayStatus(orderid);
    }

    @Override
    public List<Order> listByUseridAndConditon(Integer id, String condition) {
        return StringUtils.isEmpty(condition) ? orderMapper.listByUserid(id) : orderMapper.listByUseridAndCondition(id, condition);
    }

    @Override
    public Order getById(Integer id) {
        return orderMapper.getById(id);
    }

    @Override
    public Order getByOrderid(String orderid) {
        return orderMapper.getByOrderid(orderid);
    }

    @Override
    public List<OrderListAndItems> listOrderListAndItems(Integer id, String condition) {
        List<OrderListAndItems> orderListAndItemsList = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        if (StringUtils.isEmpty(condition)) {
            orders = orderMapper.listByUserid(id);
        } else {
            orders = orderMapper.listByUseridAndCondition(id, condition);
        }
        for (Order order : orders) {
            OrderListAndItems orderListAndItems = new OrderListAndItems();
            orderListAndItems.setOrder(order);
            List<OrderItem> items = orderItemMapper.listByOrderid(order.getId());
            orderListAndItems.setItem(items.get(0));
            orderListAndItemsList.add(orderListAndItems);
        }
        return orderListAndItemsList;
    }

    @Override
    public Integer enterReceive(Integer id) {
        return orderMapper.updateSendStatusFinish(id);
    }
}
