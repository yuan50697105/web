package com.web.service;

import com.web.bean.User;
import com.web.dao.UserDao;

public class UserService {
	private UserDao userDao=new UserDao();

	public User get(User user) {
		return userDao.get(user);
	}

}
