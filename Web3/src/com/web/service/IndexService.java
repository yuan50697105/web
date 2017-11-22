package com.web.service;

import java.util.List;

import com.web.bean.News;
import com.web.bean.Product;
import com.web.dao.NewsDao;
import com.web.dao.ProductDao;
import com.web.utils.PageModel;

public class IndexService {
	private NewsDao newsDao=new NewsDao();
	private ProductDao productDao=new ProductDao();
	public List<News> listNews(Integer length) {
		PageModel<News> pageModel=new PageModel<>();
		pageModel.setPageSize(length);
		pageModel.setPageIndex(1);
		return newsDao.listByStatus(1, pageModel).getList();
	}

	public List<Product> listProduct(Integer length) {
		PageModel<Product> pageModel=new PageModel<>();
		pageModel.setPageIndex(1);
		pageModel.setPageSize(length);
		return productDao.listByStatus(1, pageModel).getList();
	}

}
