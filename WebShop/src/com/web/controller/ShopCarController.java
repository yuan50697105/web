package com.web.controller;

import com.web.bean.Goods;
import com.web.bean.OrderItem;
import com.web.commons.WebCommons;
import com.web.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@SuppressWarnings("unchecked")
@Controller
@RequestMapping("/shopCar")
public class ShopCarController {
    @Autowired
    private ShopCarService shopCarService;


    @RequestMapping(value = "/add")
    @ResponseBody
    public Map<String, Object> addToShopCar(Integer goodsid, Integer num, HttpSession session) {
        System.out.println("goodsid=" + goodsid + " num=" + num);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Object object = session.getAttribute(WebCommons.SESSION_SHOPCAR);
        Integer totalNum = 0;
        if (null == object) {
            Map<Integer, Integer> shopCarMap = new LinkedHashMap<Integer, Integer>();
            shopCarMap.put(goodsid, num);
            session.setAttribute(WebCommons.SESSION_SHOPCAR, shopCarMap);
            totalNum += num;
        } else {
            Map<Integer, Integer> shopCarMap = (Map<Integer, Integer>) session.getAttribute(WebCommons.SESSION_SHOPCAR);
            if (shopCarMap.containsKey(goodsid)) {
                shopCarMap.replace(goodsid, shopCarMap.get(goodsid) + num);
            } else {
                shopCarMap.put(goodsid, num);
            }
            session.setAttribute(WebCommons.SESSION_SHOPCAR, shopCarMap);
            Set<Integer> set = shopCarMap.keySet();
            for (Integer integer : set) {
                totalNum += shopCarMap.get(integer);
            }
        }
        resultMap.put("totalNum", totalNum);
        resultMap.put(WebCommons.SHOP_STATUS, "添加成功");
        return resultMap;
    }

    @RequestMapping("/add/{goodsid}/{num}")
    @ResponseBody
    public Map<String, Object> addToShopCar2(@PathVariable("goodsid") Integer goodsid, @PathVariable("num") Integer num, HttpSession session) {
        return addToShopCar(goodsid, num, session);
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Map<String, Object> removeFromShopCar(Integer goodsid, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        Object object = session.getAttribute(WebCommons.SESSION_SHOPCAR);
        if (null != object) {
            Map<Integer, Integer> map = (Map<Integer, Integer>) object;
            System.out.println(map);
            if (map.containsKey(goodsid)) {
                map.remove(goodsid);
                System.out.println(map);
            }
            resultMap.put(WebCommons.SHOP_STATUS, 0);
        } else {
            resultMap.put(WebCommons.SHOP_STATUS, 1);
        }
        return resultMap;
    }

    @RequestMapping("/remove/{id}")
    @ResponseBody
    public Map<String, Object> removeFromShopCar2(@PathVariable("id") Integer goodsid, HttpSession session) {
        return removeFromShopCar(goodsid, session);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> updateShopCar(Integer goodsid, Integer num, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        Object object = session.getAttribute(WebCommons.SESSION_SHOPCAR);
        if (null != object) {
            Map<Integer, Integer> map = (Map<Integer, Integer>) object;
            if (map.containsKey(goodsid)) {
                map.replace(goodsid, num);
            }
            resultMap.put(WebCommons.SHOP_STATUS, 0);
        } else {
            resultMap.put(WebCommons.SHOP_STATUS, 1);
        }
        return resultMap;
    }

    @RequestMapping("/update/{id}/{num}")
    @ResponseBody
    public Map<String, Object> updateShopCar2(@PathVariable("id") Integer goodsid, @PathVariable("num") Integer num, HttpSession session) {
        return updateShopCar(goodsid, num, session);
    }

    @RequestMapping("/show")
    public String show(HttpSession session, Model model) {
        double totalPrice = 0;
        List<OrderItem> items = new ArrayList<>();
        Object object = session.getAttribute(WebCommons.SESSION_SHOPCAR);
        if (null != object) {
            Map<Integer, Integer> map = (Map<Integer, Integer>) object;
            Set<Integer> set = map.keySet();
            for (Integer integer : set) {
                OrderItem item = new OrderItem();
                Goods goods = shopCarService.getGoods(integer);
                item.setGoods(goods);
                item.setSum(map.get(integer));
                totalPrice += goods.getPrice() * map.get(integer);
                items.add(item);
            }
        }
        model.addAttribute("items", items);
        model.addAttribute("totalPrice", totalPrice);
        return "shop_cart";
    }
}
