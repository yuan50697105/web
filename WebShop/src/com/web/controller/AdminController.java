package com.web.controller;

import com.web.bean.User;
import com.web.commons.WebCommons;
import com.web.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "logins";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam(value = "userid") String userid,
                                     @RequestParam(value = "password") String password, @RequestParam(value = "code") String code,
                                     HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String authCode = (String) session.getAttribute(WebCommons.CODE);
        System.out.println("输入的验证码为：" + code);
        if (!authCode.equals(code)) {
            map.put(WebCommons.LOGIN_STATUS, 0);
            map.put(WebCommons.LOGIN_MSG, "验证码错误");
        } else {
            User user = adminService.login(userid, password);
            if (user == null) {
                map.put(WebCommons.LOGIN_STATUS, 0);
                map.put(WebCommons.LOGIN_MSG, "账户密码错误");
            } else if (user.getActiveStatus() != 1) {
                map.put(WebCommons.LOGIN_STATUS, 0);
                map.put(WebCommons.LOGIN_MSG, "此账户未激活");
            } else if (user.getRole() != 1) {
                map.put(WebCommons.LOGIN_STATUS, 0);
                map.put(WebCommons.LOGIN_MSG, "你不是管理员无法登录后台");
            } else {
                map.put(WebCommons.LOGIN_STATUS, 1);
                session.setAttribute("user", user);
            }
        }
        return map;
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session) {
        return "logins";
    }

    @RequestMapping("/main")
    public String toMain() {
        return "main";
    }

}
