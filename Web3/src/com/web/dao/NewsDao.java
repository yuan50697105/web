package com.web.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.web.bean.News;
import com.web.bean.NewsType;
import com.web.bean.User;
import com.web.utils.DBUtils;
import com.web.utils.PageModel;

public class NewsDao {
	private QueryRunner runner = DBUtils.getRunner();

	public Integer save(News news) {
		Integer count = 0;
		String sql = "INSERT INTO news(title,content,image,type,userid)  VALUES (?,?,?,?,?);";
		try {
			count = runner.update(sql, news.getTitle(), news.getContent(), news.getImage(), news.getNewsType().getId(),
					news.getUser().getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Integer delete(Long id) {
		Integer count = 0;
		String sql = "DELETE FROM news WHERE id=?;";
		try {
			count = runner.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Integer update(News news) {
		Integer count = 0;
		String sql = "UPDATE news SET title=?,image=?,content=?,type=? WHERE id=?;";
		try {
			count = runner.update(sql, news.getTitle(), news.getImage(), news.getContent(), news.getNewsType().getId(),
					news.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public News get(Integer id) {
		String sql = "SELECT * FROM news WHERE id=?;";
		Map<String, Object> map = null;
		try {
			map = runner.query(sql, new MapHandler(), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getNews(map);
	}

	public Integer publish(Integer[] longs) {
		Integer count = 0;
		StringBuilder stringBuilder = new StringBuilder(
				"UPDATE news SET publishStatus=1,publishTime=now() WHERE id IN (");
		for (long i = 0; i < longs.length; i++) {
			stringBuilder.append(i == 0 ? "?" : ",?");
		}
		stringBuilder.append(");");
		Object[] objects = longs;
		try {
			count = runner.update(stringBuilder.toString(), objects);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Integer allPublish() {
		Integer count = 0;
		String sql = "UPDATE news SET publishStatus=1,publishTime=now();";
		try {
			count = runner.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public PageModel<News> listAll(PageModel<News> pageModel) {
		try {
			String sql = "SELECT count(*) AS count FROM news;";
			Map<String, Object> map = runner.query(sql, new MapHandler());
			pageModel.setTotalRecord(Integer.valueOf(map.get("count").toString()));
			sql = "SELECT * FROM news LIMIT ?,?;";
			List<Map<String, Object>> maps = runner.query(sql, new MapListHandler(), pageModel.getStartIndex(),
					pageModel.getPageSize());
			pageModel.setList(getNewsList(maps));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}

	public PageModel<News> listByName(String title, PageModel<News> pageModel) {
		try {
			String sql = "SELECT count(*) AS count FROM news WHERE title LIKE concat('%',?,'%');";
			Map<String, Object> map = runner.query(sql, new MapHandler(), title);
			pageModel.setTotalRecord(Integer.valueOf(map.get("count").toString()));
			sql = "SELECT * FROM news WHERE title LIKE concat('%',?,'%')  LIMIT ?,?;";
			List<Map<String, Object>> maps = runner.query(sql, new MapListHandler(), title, pageModel.getStartIndex(),
					pageModel.getPageSize());
			pageModel.setList(getNewsList(maps));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}

	public PageModel<News> listByTypeAndStatus(Integer type, Integer status, PageModel<News> pageModel) {
		try {
			String sql = "SELECT count(*) AS count FROM news WHERE type=? AND publishStatus=?;";
			Map<String, Object> map = runner.query(sql, new MapHandler(), type, status);
			pageModel.setTotalRecord(Integer.valueOf(map.get("count").toString()));
			sql = "SELECT * FROM news WHERE type=? AND publishStatus=? LIMIT ?,?;";
			List<Map<String, Object>> maps = runner.query(sql, new MapListHandler(), type, status,
					pageModel.getStartIndex(), pageModel.getPageSize());
			pageModel.setList(getNewsList(maps));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}

	public PageModel<News> listByStatus(Integer status, PageModel<News> pageModel) {
		try {
			String sql = "SELECT count(*) AS count FROM news WHERE publishStatus=?;";
			Map<String, Object> map = runner.query(sql, new MapHandler(), status);
			pageModel.setTotalRecord(Integer.valueOf(map.get("count").toString()));
			sql = "SELECT * FROM news WHERE publishStatus=? LIMIT ?,?;";
			List<Map<String, Object>> maps = runner.query(sql, new MapListHandler(), status, pageModel.getStartIndex(),
					pageModel.getPageSize());
			pageModel.setList(getNewsList(maps));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}

	private News getNews(Map<String, Object> map) {
		News news = new News();
		try {
			BeanUtils.populate(news, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		NewsType newsType = new NewsType();
		newsType.setId((Integer) map.get("type"));
		news.setNewsType(newsType);
		User user = new User();
		user.setId((Integer) map.get("userid"));
		news.setUser(user);
		return news;
	}

	private List<News> getNewsList(List<Map<String, Object>> maps) {
		List<News> newsList = new ArrayList<>();
		for (Map<String, Object> map : maps) {
			newsList.add(getNews(map));
		}
		return newsList;
	}

}
