package com.web.controller;

import com.web.bean.User;
import com.web.commons.WebCommons;
import com.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(String userid, String password, String code, HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        String authCode = (String) session.getAttribute(WebCommons.CODE);
        if (!authCode.equals(code)) {
            map.put(WebCommons.LOGIN_STATUS, 0);
            map.put(WebCommons.LOGIN_MSG, "验证码错误");
        } else {
            User user = customerService.login(userid, password);
            if (user == null) {
                map.put(WebCommons.LOGIN_STATUS, 0);
                map.put(WebCommons.LOGIN_MSG, "账户密码错误");
            } else if (user.getActiveStatus() != 1) {
                map.put(WebCommons.LOGIN_STATUS, 0);
                map.put(WebCommons.LOGIN_MSG, "此账户未激活");
            } else {
                map.put(WebCommons.LOGIN_STATUS, 1);
                session.setAttribute("user", user);
            }
        }
        return map;
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session) {
        session.invalidate();
        return "redirect:/index";
    }
}
