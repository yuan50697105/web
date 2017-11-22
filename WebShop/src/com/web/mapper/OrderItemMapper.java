package com.web.mapper;

import com.web.bean.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderItemMapper {
    Integer save(OrderItem orderItem);

    Integer delete(Integer id);

    List<OrderItem> listByOrderid(Integer id);
}
