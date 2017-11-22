package com.web.service;

import com.web.bean.User;

public interface RegistorService {

    Integer registor(User user);

    boolean active(String userid, String activeCode);

}
