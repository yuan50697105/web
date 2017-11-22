package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/center")
public class CustomerCenterController {
    @RequestMapping("/to")
    public String toCenter(){
        return "user";
    }
}
