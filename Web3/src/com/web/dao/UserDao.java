package com.web.dao;

import com.web.bean.User;
import com.web.utils.DBUtils;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserDao {
	private QueryRunner runner = DBUtils.getRunner();

	public Integer save(User user) {
		Integer count = 0;
		String sql = "INSERT INTO user (name, password) VALUES (?,?);";
		try {
			count = runner.update(sql, user.getName(), user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Integer delete(Integer id) {
		Integer count = 0;
		String sql = "DELETE FROM user WHERE id=?;";
		try {
			count = runner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Integer udpate(User user) {
		Integer count = 0;
		String sql = "UPDATE user SET name=?,password=?,type=? WHERE id=?;";
		try {
			count = runner.update(sql, user.getName(), user.getPassword(), user.getType(), user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public User get(Integer id) {
		User user = null;
		String sql = "SELECT * FROM user WHERE id=?;";
		try {
			user = runner.query(sql, new BeanHandler<User>(User.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User get(User user) {
		String sql = "SELECT * FROM user WHERE name=? AND password=?;";
		try {
			user = runner.query(sql, new BeanHandler<User>(User.class), user.getName(), user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
}
