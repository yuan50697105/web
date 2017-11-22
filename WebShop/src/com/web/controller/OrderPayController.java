package com.web.controller;

import com.web.bean.*;
import com.web.commons.WebCommons;
import com.web.service.OrderService;
import com.web.utils.DirectPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/customer/order")
public class OrderPayController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/toSubmit")
    public String submit(String goodsArrays, HttpSession session) {
        double totalPrice = 0.0;
        if (StringUtils.isEmpty(goodsArrays)) {
            goodsArrays = (String) session.getAttribute(WebCommons.GOODS_ARRAYS);
        }
        String[] goodsArray = goodsArrays.split(",");
        Map<Integer, Integer> shopCar = (Map<Integer, Integer>) session.getAttribute(WebCommons.SESSION_SHOPCAR);
        List<OrderItem> items = new ArrayList<>();
        if (shopCar != null) {
            for (String s : goodsArray) {
                OrderItem item = new OrderItem();
                int num = shopCar.get(Integer.valueOf(s));
                item.setSum(num);
                Goods goods = orderService.getGoods(Integer.valueOf(s));
                item.setGoods(goods);
                totalPrice += goods.getFavoritePrice() * num;
                items.add(item);
            }
        }
        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("items", items);
        return "confirm_order";
    }

    @RequestMapping("/submit")
    public String submit(HttpSession session, RedirectAttributes attributes) {
        double totalPrice = (double) session.getAttribute("totalPrice");
        List<OrderItem> items = (List<OrderItem>) session.getAttribute("items");
        User user = (User) session.getAttribute(WebCommons.SESSION_USER);
        Order order = new Order();
        order.setUser(user);
        order.setOrderid(createOrderid(user.getId()));
        order.setTotalPrice(totalPrice);
        order.setSendAddress(user.getAddress());
        orderService.save(order, items);
        System.out.println(order);
        attributes.addFlashAttribute("order", order);
        return "redirect:/customer/order/toPay";
    }

    @RequestMapping("/toPay")
    public ModelAndView orderPay(@ModelAttribute Order order) {
        String returnURL = "http://127.0.0.1:8080/test/customer/order/getPayResult";
        Map<String, String> params = DirectPayService.getRequestDataByDirectPay(order.getOrderid(), "酷居客订单" + order.getOrderid(),
                order.getTotalPrice().toString(), order.getOrderid(), returnURL);
        Map<String, Object> map = new HashMap<>();
        map.put("params", params);
        map.put("requestUrl", DirectPayService.direct_alipay_gateway);
        return new ModelAndView("alipay/paying", map);
    }

    @RequestMapping("/toPay/{id}")
    public ModelAndView orderPay2(@PathVariable("id") Integer id) {
        Order order = orderService.getById(id);
        String returnURL = "http://127.0.0.1:8080/test/customer/order/getPayResult";
        Map<String, String> params = DirectPayService.getRequestDataByDirectPay(order.getOrderid(), "酷居客订单" + order.getOrderid(),
                order.getTotalPrice().toString(), order.getOrderid(), returnURL);
        Map<String, Object> map = new HashMap<>();
        map.put("params", params);
        map.put("requestUrl", DirectPayService.direct_alipay_gateway);
        return new ModelAndView("alipay/paying", map);
    }

    @RequestMapping("/getPayResult")
    public String payResult(String out_trade_no, String trade_no, String trade_status, String buyer_id, String total_fee, Model model, HttpSession session) {
        System.out.println("trade_no:" + trade_no);
        System.out.println("trade_status:" + trade_status);
        System.out.println("buyer_id:" + buyer_id);
        System.out.println("total_fee:" + total_fee);
        Order order = orderService.getByOrderid(out_trade_no);
        session.setAttribute(WebCommons.SESSION_USER, order.getUser());
        if (trade_status.equals("TRADE_SUCCESS") || trade_status.equals("TRADE_FINISH")) {
            orderService.updateAlipay(out_trade_no);
            model.addAttribute("order", order);
            return "pay_success";
        } else {
            return "pay_failure";
        }
    }

    @RequestMapping("/listUserOrder")
    public String listUserOrders(HttpSession session, String condition, @RequestParam(defaultValue = "0") Integer page, Model model) {
        User user = (User) session.getAttribute(WebCommons.SESSION_USER);
        List<OrderListAndItems> orderListAndItems = orderService.listOrderListAndItems(user.getId(), condition);
        model.addAttribute("list", orderListAndItems);
        model.addAttribute("condition", condition);
        return "order";
    }

    @RequestMapping("/getOrderDetail")
    public String orderDetail(Integer id, Model model) {
        List<OrderItem> items = orderService.listOrderItems(id);
        Order order = orderService.getById(id);
        model.addAttribute("items", items);
        model.addAttribute("order", order);
        return "order_details";
    }

    @RequestMapping("/getOrderDetail/{id}")
    public String orderDetail2(@PathVariable("id") Integer id, Model model) {
        return orderDetail(id, model);
    }

    @RequestMapping("/enterRecive/{id}")
    public String enterOrderReceive(@PathVariable("id") Integer id, HttpSession session,Model model,RedirectAttributes attributes) {
        Integer rows = orderService.enterReceive(id);
        attributes.addFlashAttribute("tip", rows > 0);
        User user = (User) session.getAttribute(WebCommons.SESSION_USER);
        List<OrderListAndItems> orderListAndItems = orderService.listOrderListAndItems(user.getId(), null);
        model.addAttribute("list", orderListAndItems);
        return "order";
    }

    private String createOrderid(Integer id) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        return id.toString() + dateFormat.format(new Date());
    }
}
