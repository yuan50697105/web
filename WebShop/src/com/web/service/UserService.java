package com.web.service;

import com.web.bean.User;

import java.util.List;

public interface UserService {

    List<User> list(String userid);

    Integer save(User user);

    Integer delete(Integer id);

    User getUser(Integer id);

    Integer update(User user);

}
