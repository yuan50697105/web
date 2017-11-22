package com.web.utils;

import com.web.bean.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtils {
    public static String orderCreator(User user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        return user.getId().toString() + dateFormat.format(new Date());
    }
}
