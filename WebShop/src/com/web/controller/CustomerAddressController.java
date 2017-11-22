package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/address")
public class CustomerAddressController {
    @RequestMapping("/to")
    public String address(){
        return "address";
    }
}
