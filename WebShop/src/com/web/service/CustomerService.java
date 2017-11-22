package com.web.service;

import com.web.bean.User;

public interface CustomerService {

    User login(String userid, String password);

}
