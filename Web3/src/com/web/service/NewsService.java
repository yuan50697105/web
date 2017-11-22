package com.web.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.web.bean.News;
import com.web.bean.NewsType;
import com.web.dao.NewsDao;
import com.web.dao.NewsTypeDao;
import com.web.dao.UserDao;
import com.web.utils.PageModel;

public class NewsService {
	private NewsDao newsDao = new NewsDao();
	private NewsTypeDao newsTypeDao = new NewsTypeDao();
	private UserDao userDao = new UserDao();

	private PageModel<News> list(PageModel<News> pageModel) {
		List<News> newsList = pageModel.getList();
		for (News news : newsList) {
			news.setNewsType(newsTypeDao.get(news.getNewsType().getId()));
			news.setUser(userDao.get(news.getUser().getId()));
		}
		return pageModel;
	}

	private News getNews(News news) {
		news.setUser(userDao.get(news.getUser().getId()));
		news.setNewsType(newsTypeDao.get(news.getNewsType().getId()));
		return news;
	}

	public List<NewsType> listNewsType() {
		return newsTypeDao.list();
	}

	public Integer save(News news) {
		return newsDao.save(news);
	}

	public Integer delete(Long id) {
		return newsDao.delete(id);
	}

	public News get(Integer id) {
		return getNews(newsDao.get(id));
	}

	public Integer update(News news) {
		return newsDao.update(news);
	}

	public Integer publish(Integer[] longs) {
		return newsDao.publish(longs);
	}

	public PageModel<News> listAll(PageModel<News> pageModel) {
		PageModel<News> pageModel2 = newsDao.listAll(pageModel);
		return list(pageModel2);
	}

	public PageModel<News> listByName(String title, PageModel<News> pageModel) {
		PageModel<News> pageModel2 = StringUtils.isNotEmpty(title) ? newsDao.listAll(pageModel)
				: newsDao.listByName(title, pageModel);
		return list(pageModel2);
	}

	public PageModel<News> listByTypeAndStatus(Integer type, Integer status, PageModel<News> pageModel) {
		PageModel<News> pageModel2 = type == null ? newsDao.listByStatus(status, pageModel)
				: newsDao.listByTypeAndStatus(type, status, pageModel);
		return list(pageModel2);
	}

	public Integer allPublish() {
		return newsDao.allPublish();
	}
}
