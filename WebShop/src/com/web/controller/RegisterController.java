package com.web.controller;

import com.web.bean.User;
import com.web.commons.WebCommons;
import com.web.service.RegistorService;
import com.web.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {
    @Autowired
    private RegistorService registorService;

    @RequestMapping("/toRegistor")
    public String toReg() {
        return "register";
    }

    @RequestMapping("/reg")
    public String reg(User user, HttpServletRequest request, RedirectAttributes attributes) {
        System.err.println(user);
        String activeCode = String.valueOf(System.currentTimeMillis());
//        String activeURL = "http://localhost:8080/test/active?userid=" + user.getUserid() + "&activeCode="
//                + activeCode;
        String activeURL = "http://127.0.0.1:8080" + request.getContextPath() + "/active/" + user.getUserid() + "/" + activeCode;
        StringBuilder content = new StringBuilder();
        content.append(user.getUserid() + " 亲,您好，欢迎您使用我们商城，请点击下面的链接进行帐号激活:<br/>");
        content.append("<a href='" + activeURL + "'>" + activeURL + "</a>");
        boolean flag = MailUtils.send(user.getEmail(), "酷居客注册激活", content.toString());
        System.err.println("flag=" + flag);
        if (flag) {
            user.setActiveCode(activeCode);
            user.setName(user.getUserid());
            Integer rows = registorService.registor(user);
            attributes.addFlashAttribute(WebCommons.TIP, rows > 0);
        }
        return "redirect:/index";
    }

    @RequestMapping("/active")
    public String active(String userid, String activeCode, RedirectAttributes attributes) {
        boolean result = registorService.active(userid, activeCode);
        attributes.addFlashAttribute(WebCommons.TIP, result);
        return "redirect:/index";
    }

    @RequestMapping("/active/{userid}/{active}")
    public String active2(@PathVariable("userid") String userid, @PathVariable("active") String activceCode, RedirectAttributes attributes) {
        return active(userid, activceCode, attributes);
    }
}
