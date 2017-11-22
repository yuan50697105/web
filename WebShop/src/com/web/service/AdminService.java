package com.web.service;

import com.web.bean.User;

public interface AdminService {

    User login(String userid, String password);

}
