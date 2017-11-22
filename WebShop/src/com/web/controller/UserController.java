package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.bean.User;
import com.web.commons.WebCommons;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String list(String userid, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PageHelper.startPage(page, 5, true);
        List<User> list = userService.list(userid);
        model.addAttribute("page", new PageInfo<User>(list));
        model.addAttribute("userid", userid);
        return "user/List";
    }

    @RequestMapping("/list/{userid}/{page}")
    public String list2(@PathVariable("userid") String userid,
                        @PathVariable(value = "page", required = false) @RequestParam(defaultValue = "1") Integer page,
                        Model model) {
        return list(userid, page, model);
    }

    @RequestMapping("/toSave")
    public String toSave() {
        return "user/toSave";
    }

    @RequestMapping("/save")
    public String save(User user, RedirectAttributes attributes) {
        Integer rows = userService.save(user);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id, RedirectAttributes attributes) {
        Integer rows = userService.delete(id);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete2(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        return delete(id, attributes);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user/Update";
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate2(@PathVariable("id") Integer id, Model model) {
        return toUpdate(id, model);
    }

    @RequestMapping("/update")
    public String update(User user, RedirectAttributes attributes) {
        Integer rows = userService.update(user);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/user/list";
    }
}
