package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.bean.Picture;
import com.web.commons.WebCommons;
import com.web.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/list")
    public String list(String title, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PageHelper.startPage(page, 5, true);
        List<Picture> list = pictureService.list(title);
        PageInfo<Picture> pageInfo = new PageInfo<Picture>(list);
        model.addAttribute("page", pageInfo);
        model.addAttribute("title", title);
        return "picture/List";
    }

    @RequestMapping("/list/{title}/{page}")
    public String list2(@PathVariable(value = "title") String title,
                        @PathVariable(value = "page", required = false) @RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        return list(title, page, model);
    }

    @RequestMapping("/toSave")
    public String toSave() {
        return "picture/Save";
    }

    @RequestMapping("/save")
    public String save(Picture picture, MultipartFile file, HttpServletRequest request,
                       RedirectAttributes attributes) {
        if (0 != file.getSize()) {
            String imageUrl = WebCommons.PIC_IMG_PATH + file.getOriginalFilename();
            String path = request.getServletContext().getRealPath(imageUrl);
            try {
                file.transferTo(new File(path));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            picture.setImageURL(imageUrl);
        }
        Integer rows = pictureService.save(picture);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/picture/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id, RedirectAttributes attributes) {
        Integer rows = pictureService.delete(id);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/picture/delete";
    }

    @RequestMapping("/delete/{id}")
    public String delete2(@PathVariable(value = "id") Integer id, RedirectAttributes attributes) {
        return delete(id, attributes);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {
        Picture picture = pictureService.getPicture(id);
        model.addAttribute("picture", picture);
        return "picture/Update";
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate2(@PathVariable(value = "id") Integer id, Model model) {
        return toUpdate(id, model);
    }

    @RequestMapping("/update")
    public String update(Picture picture, MultipartFile file, HttpServletRequest request,
                         RedirectAttributes attributes) {
        if (0 != file.getSize()) {
            String imageUrl = WebCommons.PIC_IMG_PATH + file.getOriginalFilename();
            String path = request.getServletContext().getRealPath(imageUrl);
            try {
                file.transferTo(new File(path));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            picture.setImageURL(imageUrl);
        }
        Integer rows = pictureService.update(picture);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/picture/list";
    }
}
