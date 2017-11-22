import com.web.bean.Order;
import com.web.bean.OrderItem;
import com.web.mapper.OrderItemMapper;
import com.web.mapper.OrderMapper;
import com.web.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springmvc.xml"})
public class Test1 {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Test
    public void test() {
//        System.out.println("length:"+orderService.listOrderListAndItems(1,null).size());
        List<Order> orders = orderMapper.listByUserid(1);
        /*for (Order order:orders){
            System.out.println(order);
        }*/
        for (Order order : orders) {
            List<OrderItem> items = orderItemMapper.listByOrderid(order.getId());
            System.out.println(items);
        }
    }
}
