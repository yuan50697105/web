package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.bean.Order;
import com.web.bean.OrderItem;
import com.web.commons.WebCommons;
import com.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/list")
    public String list(String condition, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PageHelper.startPage(page, 5);
        List<Order> list = orderService.listByCondition(condition);
        PageInfo<Order> pageInfo = new PageInfo<Order>(list);
        model.addAttribute("page", pageInfo);
        model.addAttribute("condition", condition);
        return "order/orderList";
    }

    @RequestMapping("/list/{condition}/{page}")
    public String list2(@PathVariable(value = "condition", required = false) String condition,
                        @PathVariable(value = "page", required = false) @RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        return list(condition, page, model);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {
        Order order = orderService.getById(id);
        model.addAttribute("order", order);
        return "order/Update";
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate2(@PathVariable(value = "id") Integer id, Model model) {
        return toUpdate(id, model);
    }

    @RequestMapping("/update")
    public String updateSendStatus(Integer id, Integer status, RedirectAttributes attributes) {
        Integer rows = orderService.updateSendStatus(id, status);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/order/list";
    }

    @RequestMapping("/list/items")
    public String listOrderItem(Integer id, Model model) {
        List<OrderItem> items = orderService.listOrderItems(id);
        model.addAttribute("items", items);
        return "order/orderItem";
    }
}
