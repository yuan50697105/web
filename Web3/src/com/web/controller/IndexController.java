package com.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.bean.News;
import com.web.bean.Product;
import com.web.service.IndexService;

@WebServlet("/index")
public class IndexController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -119711966049845217L;
	private IndexService indexService = new IndexService();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> products=indexService.listProduct(8);
		List<News> news=indexService.listNews(8);
		request.setAttribute("products", products);
		request.setAttribute("news", news);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	

}
