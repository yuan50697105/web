package com.web.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.web.bean.ProType;
import com.web.bean.Product;
import com.web.dao.ProTypeDao;
import com.web.dao.ProductDao;
import com.web.dao.UserDao;
import com.web.utils.PageModel;

public class ProductService {
	private ProductDao productDao = new ProductDao();
	private UserDao userDao = new UserDao();
	private ProTypeDao proTypeDao = new ProTypeDao();

	private PageModel<Product> list(PageModel<Product> pageModel) {
		List<Product> products = pageModel.getList();
		for (Product product : products) {
			product = getProduct(product);
		}
		return pageModel;
	}

	private Product getProduct(Product product) {
		product.setUser(userDao.get(product.getUser().getId()));
		product.setProType(proTypeDao.get(product.getProType().getId()));
		return product;
	}

	public List<ProType> listProType() {
		return proTypeDao.listAll();
	}

	public Integer save(Product product) {
		return productDao.save(product);
	}

	public Integer delete(Integer id) {
		return productDao.delete(id);
	}

	public Product get(Integer id) {
		return productDao.get(id);
	}

	public Integer update(Product product) {
		return productDao.update(product);
	}

	public Integer publish(Integer[] ids) {
		return productDao.publish(ids);
	}
	public Integer allPublish() {
		return productDao.allPublish();
	}
	public PageModel<Product> listAll(PageModel<Product> pageModel) {
		return list(productDao.listAll(pageModel));
	}

	public PageModel<Product> listByName(String name, PageModel<Product> pageModel) {
		PageModel<Product> pageModel2 = StringUtils.isNotEmpty(name) ? productDao.listByName(name, pageModel)
				: productDao.listAll(pageModel);
		return list(pageModel2);
	}

	public PageModel<Product> listByTypeAndStatus(Integer type, Integer status, PageModel<Product> pageModel) {
		PageModel<Product> pageModel2=type==null?productDao.listByStatus(status, pageModel):productDao.listByTypeAndStatus(type, status, pageModel);
		return list(pageModel2);
	}

	

}
