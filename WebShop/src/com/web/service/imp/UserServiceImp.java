package com.web.service.imp;

import com.web.bean.User;
import com.web.mapper.UserMapper;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list(String userid) {
        return StringUtils.isEmpty(userid) ? userMapper.listAll() : userMapper.listByUserid(userid);
    }

    @Override
    public Integer save(User user) {
        return userMapper.save(user);
    }

    @Override
    public Integer delete(Integer id) {
        return userMapper.delete(id);
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public Integer update(User user) {
        return userMapper.updateInfo(user);
    }

}
