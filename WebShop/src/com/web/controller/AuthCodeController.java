package com.web.controller;

import com.web.commons.WebCommons;
import com.web.utils.AuthCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AuthCodeController {
    @RequestMapping("/getAuthCode")
    public void getCode(HttpSession session, HttpServletResponse response) {
        String authCode = AuthCodeUtils.getCode();
        session.setAttribute(WebCommons.CODE, authCode);
        System.out.println(authCode);
        try {
            ImageIO.write(AuthCodeUtils.getCodeImg(authCode), "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
