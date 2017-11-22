package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.bean.TimeLimit;
import com.web.commons.WebCommons;
import com.web.service.TimeLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/time")
public class TimeLimitController {
    @Autowired
    private TimeLimitService timeLimitService;

    @RequestMapping("/list")
    public String list(String title, @RequestParam(defaultValue = "1") Integer page, Model model) {
        PageHelper.startPage(page, 5, true);
        List<TimeLimit> list = timeLimitService.list(title);
        PageInfo<TimeLimit> pageInfo = new PageInfo<TimeLimit>(list);
        model.addAttribute("page", pageInfo);
        model.addAttribute("title", title);
        return "time/List";
    }

    @RequestMapping("/list/{title}/{page}")
    public String list2(@PathVariable(value = "title") String title,
                        @PathVariable(value = "page", required = false) @RequestParam(defaultValue = "1") Integer page, Model model) {
        return list(title, page, model);
    }

    @RequestMapping("/toSave")
    public String toSave() {
        return "time/Save";
    }

    @RequestMapping("/save")
    public String save(TimeLimit timeLimit, RedirectAttributes attributes) {
        Integer rows = timeLimitService.save(timeLimit);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/time/list";
    }

    @RequestMapping("/delete")
    public String delete(Integer id, RedirectAttributes attributes) {
        Integer rows = timeLimitService.delete(id);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/time/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete2(@PathVariable(value = "id") Integer id, RedirectAttributes attributes) {
        return delete(id, attributes);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {
        TimeLimit timeLimit = timeLimitService.getTimeLimit(id);
        model.addAttribute("time", timeLimit);
        return "time/Update";
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate2(@PathVariable(value = "id") Integer id, Model model) {
        return toUpdate(id, model);
    }

    @RequestMapping("/update")
    public String update(TimeLimit timeLimit, RedirectAttributes attributes) {
        Integer rows = timeLimitService.update(timeLimit);
        attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        return "redirect:/admin/time/list";
    }
}
