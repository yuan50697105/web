package com.web.service.imp;

import com.web.bean.User;
import com.web.mapper.UserMapper;
import com.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userid, String password) {
        return userMapper.getByUseridAndPassowrd(userid, password);
    }

}
