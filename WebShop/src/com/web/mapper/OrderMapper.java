package com.web.mapper;

import com.web.bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {
    Integer save(Order order);

    Integer delete(Integer id);

    Integer updateSendStatus(@Param("id") Integer id, @Param("status") Integer status);

    Integer updateAlipayStatus(String orderid);

    Integer updateSendStatusFinish(Integer id);

    Order getById(Integer id);

    List<Order> listAll();

    List<Order> listByCondition(String condition);

    List<Order> listByUserid(Integer userid);

    List<Order> listByUseridAndCondition(Integer id, String condition);

    Order getByOrderid(String orderid);


}
