package com.web.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.web.bean.ProType;
import com.web.utils.DBUtils;

public class ProTypeDao {
	private QueryRunner runner = DBUtils.getRunner();

	public Integer save(ProType proType) {
		Integer count = 0;
		String sql = "INSERT INTO protype (name) VALUES (?);";
		try {
			count = runner.update(sql, proType.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Integer delete(Integer id) {
		Integer count = 0;
		String sql = "DELETE FROM protype WHERE id=?:";
		try {
			count = runner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Integer update(ProType proType) {
		Integer count = 0;
		String sql = "UPDATE protype SET name=? WHERE id=?;";
		try {
			count = runner.update(sql, proType.getName(), proType.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ProType get(Integer id) {
		ProType proType = null;
		String sql = "SELECT * FROM protype WHERE id=?;";
		try {
			proType = runner.query(sql, new BeanHandler<ProType>(ProType.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proType;
	}

	public List<ProType> listAll() {
		List<ProType> proTypes = new ArrayList<ProType>();
		String sql = "SELECT * FROM protype ORDER BY id ASC;";
		try {
			proTypes = runner.query(sql, new BeanListHandler<ProType>(ProType.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proTypes;
	}
}
