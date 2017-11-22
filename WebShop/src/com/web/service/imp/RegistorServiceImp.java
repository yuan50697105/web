package com.web.service.imp;

import com.web.bean.User;
import com.web.mapper.UserMapper;
import com.web.service.RegistorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistorServiceImp implements RegistorService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer registor(User user) {
        return userMapper.save(user);
    }

    @Override
    public boolean active(String userid, String activeCode) {
        User user = userMapper.getByUserid(userid);
        if (user.getActiveCode().equals(activeCode)) {
            userMapper.updateActiveStatus(userid);
            return true;
        } else {
            return false;
        }
    }

}
