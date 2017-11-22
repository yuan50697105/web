package com.web.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.web.utils.DBUtils;
import com.web.bean.NewsType;

public class NewsTypeDao {
	private QueryRunner runner = DBUtils.getRunner();

	public Integer save(NewsType newsType) {
		Integer count = 0;
		String sql = "INSERT INTO newstype (name) VALUES (?);";
		try {
			count = runner.update(sql, newsType.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Integer delete(Integer id) {
		Integer count = 0;
		String sql = "DELETE FROM newstype WHERE id=?;";
		try {
			count = runner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Integer update(NewsType newsType) {
		Integer count = 0;
		String sql = "UPDATE newstype SET name=? WHERE id=?;";
		try {
			count = runner.update(sql, newsType.getName(), newsType.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public NewsType get(Integer id) {
		NewsType newsType = null;
		String sql = "SELECT * FROM newstype WHERE id=?;";
		try {
			newsType = runner.query(sql, new BeanHandler<NewsType>(NewsType.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newsType;
	}

	public List<NewsType> list() {
		List<NewsType> newsTypes = new ArrayList<NewsType>();
		String sql = "SELECT * FROM newstype ORDER BY id ASC;";
		try {
			newsTypes = runner.query(sql, new BeanListHandler<NewsType>(NewsType.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newsTypes;
	}
}
