package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.bean.*;
import com.web.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BaseController {
    private final String LEFT_PAGE = "leftPage";
    private final String RIGTHT_PAGE = "rightPage";
    private final String CONDITION = "condition";
    @Autowired
    private BaseService baseService;

    @RequestMapping("/index")
    public String index(Model model) {

        List<Picture> pictures = baseService.listPictures();
        List<TimeLimit> limits = baseService.listTimeLimit();
        model.addAttribute("pictures", pictures);
        model.addAttribute("limits", limits);
        return "home";
    }

    @RequestMapping("/listAllTypesByAjax")
    @ResponseBody
    public List<FatherAndChildType> listAllGoodsTypes() {
        return baseService.listTypes();
    }

    @RequestMapping("/goods/search")
    public String goodsList(String condition, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PageHelper.startPage(page, 16, true);
        List<Goods> list = baseService.listGoodsByCondition(condition);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(detailProcess(list));
        model.addAttribute(CONDITION, condition);
        model.addAttribute(RIGTHT_PAGE, pageInfo);
        PageHelper.startPage(1, 4, true);
        list = baseService.listGoods();
        model.addAttribute(LEFT_PAGE, new PageInfo<Goods>(detailProcess(list)));
        return "shop_list";
    }

    @RequestMapping({"/goods/search/{condition}", "/goods/search/{condition}/{page}"})
    public String listGoodsList2(@PathVariable(value = "condition", required = false) String condition,
                                 @PathVariable(value = "page", required = false) @RequestParam(defaultValue = "1") Integer page,
                                 Model model) {
        return goodsList(condition, page, model);
    }

    @RequestMapping("/goods/type")
    public String goodsList(Integer type, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PageHelper.startPage(page, 16, true);
        List<Goods> list = baseService.listGoodsByType(type);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(detailProcess(list));
        model.addAttribute(RIGTHT_PAGE, pageInfo);
        PageHelper.startPage(1, 4);
        list = baseService.listGoods();
        model.addAttribute(LEFT_PAGE, new PageInfo<Goods>(detailProcess(list)));
        GoodsType goodsType = baseService.getGoodsType(type);
        model.addAttribute(CONDITION, goodsType.getName());
        return "shop_list";
    }

    @RequestMapping("/goods/type/{type}")
    public String goodsList2(@PathVariable(value = "type", required = true) Integer type, @RequestParam(defaultValue = "1") Integer page, Model model) {
        return goodsList(type, page, model);
    }

    @RequestMapping("/goods/detail")
    public String detail(Integer goodsid, Model model) {
        Goods goods = baseService.getGoods(goodsid);
        String fatherTypeName = baseService.getFatherTypeName(goods.getGoodsType().getId());
        model.addAttribute("fatherType", fatherTypeName);
        model.addAttribute("goods", goods);
        return "pro_details";
    }

    @RequestMapping("/goods/detail/{goodsid}")
    public String detail2(@PathVariable("goodsid") Integer goodsid, Model model) {
        return detail(goodsid, model);
    }

    private List<Goods> detailProcess(List<Goods> goods) {
        for (Goods g : goods) {
            String detail = g.getDetail();
            if (detail.startsWith("<p>") && detail.endsWith("</p>")) {
                detail = detail.substring(detail.indexOf("<p>"), detail.lastIndexOf("</p>"));
                if (detail.length() > 40) {
                    detail = detail.substring(0, 40);
                }
                g.setDetail(detail);
            }
        }
        return goods;
    }
}
