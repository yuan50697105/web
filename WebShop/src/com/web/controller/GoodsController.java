package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.bean.Goods;
import com.web.commons.WebCommons;
import com.web.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/list")
    public String list(String conditon, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PageHelper.startPage(page, 5, true);
        List<Goods> list = goodsService.list(conditon);
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(list);
        model.addAttribute("page", pageInfo);
        model.addAttribute("condition", conditon);
        return "goods/List";
    }

    @RequestMapping("/list/{condition}")
    public String list2(@PathVariable(value = "condition", required = false) String conditon, @RequestParam(defaultValue = "1") Integer page, Model model) {
        return list(conditon, page, model);
    }

    @RequestMapping("/toSave")
    public String toSave() {
        return "goods/Save";
    }

    @RequestMapping("/save")
    public String save(Goods goods, MultipartFile file, HttpServletRequest request,
                       RedirectAttributes attributes) {
        if (0 != file.getSize()) {
            String image = WebCommons.GOODS_IMAGE_PATH + file.getOriginalFilename();
            String path = request.getServletContext().getRealPath(image);
            System.out.println(path);
            try {
                file.transferTo(new File(path));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            goods.setImage(image);
        }
        Integer rows = goodsService.save(goods);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/goods/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id, RedirectAttributes attributes) {
        Integer rows = goodsService.delete(id);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/goods/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete2(@PathVariable(value = "id", required = false) Integer id, RedirectAttributes attributes) {
        return delete(id, attributes);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {
        Goods goods = goodsService.getGoods(id);
        model.addAttribute("goods", goods);
        return "goods/Update";
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate2(@PathVariable(value = "id", required = false) Integer id, Model model) {
        return toUpdate(id, model);
    }

    @RequestMapping("/update")
    public String update(Goods goods, MultipartFile file, HttpServletRequest request, RedirectAttributes attributes) {
        if (0 != file.getSize()) {
            String image = WebCommons.GOODS_IMAGE_PATH + file.getName();
            String path = request.getServletContext().getRealPath(image);
            try {
                file.transferTo(new File(path));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            goods.setImage(image);
        }
        Integer rows = goodsService.update(goods);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/goods/list";
    }

    @RequestMapping("/listAllGoodsByAjax")
    @ResponseBody
    public List<Goods> listAllGoods() {
        return goodsService.listAll();
    }
}
