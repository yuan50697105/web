package com.web.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.web.bean.ProType;
import com.web.bean.Product;
import com.web.bean.User;
import com.web.utils.DBUtils;
import com.web.utils.PageModel;

public class ProductDao {
	private QueryRunner runner = DBUtils.getRunner();
	private Integer rows = 0;

	private Product product(Map<String, Object> map) {
		Product product = new Product();
		try {
			BeanUtils.populate(product, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		ProType proType = new ProType();
		proType.setId((Integer) map.get("type"));
		product.setProType(proType);
		User user = new User();
		user.setId((Integer) map.get("userid"));
		product.setUser(user);
		return product;
	}

	private List<Product> products(List<Map<String, Object>> maps) {
		List<Product> products = new ArrayList<>();
		for (Map<String, Object> map : maps) {
			products.add(product(map));
		}
		return products;
	}

	public Integer save(Product product) {
		String sql = "INSERT INTO product(name, image, content, type,userid) VALUES (?,?,?,?,?);";
		try {
			rows = runner.update(sql, product.getName(), product.getImage(), product.getContent(),
					product.getProType().getId(), product.getUser().getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	public Integer delete(Integer id) {
		String sql = "DELETE FROM product WHERE id=?;";
		try {
			rows = runner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	public Integer update(Product product) {
		String sql = "UPDATE product SET name=?,image=?,content=?,type=? WHERE id=?;";
		try {
			rows = runner.update(sql, product.getName(), product.getImage(), product.getContent(),
					product.getProType().getId(), product.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	public Integer publish(Integer[] ids) {
		StringBuilder sql = new StringBuilder("UPDATE product SET publishStatus=1,publishTime=now() WHERE id IN (");
		for (int i = 0; i < ids.length; i++) {
			sql.append(i == 0 ? "?" : ",?");
		}
		sql.append(");");
		Object[] objects = ids;
		try {
			rows = runner.update(sql.toString(), objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	public Integer allPublish() {
		String sql="UPDATE product SET publishStatus=1,publishTime=now()";
		try {
			rows=runner.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	public Product get(Integer id) {
		Product product = null;
		String sql = "SELECT * FROM product WHERE id=?;";
		try {
			Map<String, Object> map = runner.query(sql, new MapHandler(), id);
			product = product(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public PageModel<Product> listAll(PageModel<Product> pageModel) {
		try {
			String sql = "SELECT count(*) AS count FROM product;";
			Map<String, Object> map = runner.query(sql, new MapHandler());
			pageModel.setTotalRecord(Integer.valueOf(map.get("count").toString()));
			sql = "SELECT * FROM product LIMIT ?,?;";
			List<Map<String, Object>> maps = runner.query(sql, new MapListHandler(), pageModel.getStartIndex(),
					pageModel.getPageSize());
			pageModel.setList(products(maps));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}

	public PageModel<Product> listByName(String name, PageModel<Product> pageModel) {
		try {
			String sql = "SELECT count(*) AS count FROM product WHERE name LIKE concat('%',?,'%');";
			Map<String, Object> map = runner.query(sql, new MapHandler(), name);
			pageModel.setTotalRecord(Integer.valueOf(map.get("count").toString()));
			sql = "SELECT * FROM product WHERE name LIKE concat('%',?,'%') LIMIT ?,?;";
			List<Map<String, Object>> maps = runner.query(sql, new MapListHandler(), name, pageModel.getStartIndex(),
					pageModel.getPageSize());
			pageModel.setList(products(maps));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}

	public PageModel<Product> listByTypeAndStatus(Integer type, Integer status, PageModel<Product> pageModel) {
		try {
			String sql = "SELECT count(*) AS count FROM product WHERE type=? AND publishStatus=?;";
			Map<String, Object> map = runner.query(sql, new MapHandler(), type, status);
			pageModel.setTotalRecord(Integer.valueOf(map.get("count").toString()));
			sql = "SELECT * FROM product WHERE type=? AND publishStatus=? LIMIT ?,?;";
			List<Map<String, Object>> maps = runner.query(sql, new MapListHandler(), type, status,
					pageModel.getStartIndex(), pageModel.getPageSize());
			pageModel.setList(products(maps));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}

	public PageModel<Product> listByStatus(Integer status, PageModel<Product> pageModel) {
		try {
			String sql = "SELECT count(*) AS count FROM product WHERE publishStatus=?;";
			Map<String, Object> map = runner.query(sql, new MapHandler(), status);
			pageModel.setTotalRecord(Integer.valueOf(map.get("count").toString()));
			sql = "SELECT * FROM product WHERE publishStatus=? LIMIT ?,?;";
			List<Map<String, Object>> maps = runner.query(sql, new MapListHandler(), status, pageModel.getStartIndex(),
					pageModel.getPageSize());
			pageModel.setList(products(maps));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}

	
}
