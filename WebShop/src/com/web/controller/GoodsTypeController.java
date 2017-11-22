package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.bean.GoodsType;
import com.web.commons.WebCommons;
import com.web.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/goodsType")
public class GoodsTypeController {
    @Autowired
    private GoodsTypeService goodsTypeService;

    @RequestMapping("/list")
    public String list(String name, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PageHelper.startPage(page, 5, true);
        List<GoodsType> list = goodsTypeService.list(name);
        PageInfo<GoodsType> pageInfo = new PageInfo<GoodsType>(list);
        model.addAttribute("page", pageInfo);
        model.addAttribute("name", name);
        return "goodsType/List";
    }

    @RequestMapping("/list/{name}")
    public String list2(@PathVariable(value = "name", required = false) String name, @RequestParam(defaultValue = "1") Integer page, Model model) {
        return list(name, page, model);
    }

    @RequestMapping("/toSave")
    public String toSave() {
        return "goodsType/Save";
    }

    @RequestMapping("/save")
    public String save(GoodsType goodsType, RedirectAttributes attributes) {
        Integer rows = goodsTypeService.save(goodsType);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/goodsType/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id, RedirectAttributes attributes) {
        Integer rows = goodsTypeService.delete(id);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/goodsType/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete2(@PathVariable(value = "id", required = false) Integer id, RedirectAttributes attributes) {
        return delete(id, attributes);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {
        GoodsType goodsType = goodsTypeService.getGoodsType(id);
        model.addAttribute("goodsType", goodsType);
        return "goodsType/Update";
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate2(@PathVariable(value = "id", required = false) Integer id, Model model) {
        return toUpdate(id, model);
    }

    @RequestMapping("/update")
    public String update(GoodsType goodsType, RedirectAttributes attributes) {
        Integer rows = goodsTypeService.update(goodsType);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/goodsType/list";
    }

    @RequestMapping("/listFatherTypeByAjax")
    @ResponseBody
    public List<GoodsType> listFatherTypes() {
        return goodsTypeService.listFatherTypes();
    }

    @RequestMapping("/listAllTypesByAjax")
    @ResponseBody
    public List<GoodsType> listAllTypes() {
        System.out.println("GoodsTypeController.listAllTypes()");
        return goodsTypeService.listAll();
    }

}
