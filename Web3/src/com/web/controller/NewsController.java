package com.web.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

import com.web.bean.News;
import com.web.bean.NewsType;
import com.web.bean.User;
import com.web.service.NewsService;
import com.web.utils.PageModel;

@WebServlet("/news")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10)
public class NewsController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3262319480804320571L;
	private NewsService newsService = new NewsService();
	private PageModel<News> pageModel = new PageModel<News>();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (StringUtils.isNotEmpty(method)) {
			switch (method) {
			case "toSave":
				toSave(request, response);
				break;
			case "save":
				save(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			case "toUpdate":
				toUpdate(request, response);
				break;
			case "update":
				update(request, response);
				break;
			case "publish":
				publish(request, response);
				break;
			case"allPublish":
				allPublish(request,response);
				break;
			case "listAll":
				listAll(request, response);
				break;
			case "listByName":
				listByName(request, response);
				break;
			case "listByType":
				listByTypeAndStatus(request, response);
				break;
			case "getDetail":
				getDetail(request, response);
				break;
			case "toNewsCenter":
				toNewsCenter(request, response);
				break;
			default:
				break;
			}
		}
	}

	private void toSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<NewsType> newsTypes = newsService.listNewsType();
		request.setAttribute("newsTypes", newsTypes);
		request.getRequestDispatcher("news/save.jsp").forward(request, response);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		News news = new News();
		try {
			BeanUtils.populate(news, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		NewsType newsType = new NewsType();
		newsType.setId((Integer) ConvertUtils.convert(request.getParameter("type"), Integer.class));
		news.setNewsType(newsType);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		news.setUser(user);
		Part part = request.getPart("file");
		String path = request.getServletContext().getRealPath("/upload/news");
		String fileName = path + File.separator + part.getSubmittedFileName();
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
		part.write(fileName);
		news.setImage(part.getSubmittedFileName());
		Integer count = newsService.save(news);
		String tip = count > 0 ? "添加成功" : "添加失败";
		request.setAttribute("tip", tip);
		request.getRequestDispatcher("news?method=listAll").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long id = (Long) ConvertUtils.convert(request.getParameter("id"), Long.class);
		Integer count = newsService.delete(id);
		String tip = count > 0 ? "删除成功" : "删除失败";
		request.setAttribute("tip", tip);
		request.getRequestDispatcher("news?method=listAll").forward(request, response);
	}

	private void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = (Integer) ConvertUtils.convert(request.getParameter("id"), Integer.class);
		News news = newsService.get(id);
		List<NewsType> newsTypes = newsService.listNewsType();
		request.setAttribute("newsTypes", newsTypes);
		request.setAttribute("news", news);
		request.getRequestDispatcher("news/update.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		News news = new News();
		try {
			BeanUtils.populate(news, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		NewsType newsType = new NewsType();
		newsType.setId((Integer) ConvertUtils.convert(request.getParameter("type"), Integer.class));
		news.setNewsType(newsType);
		Part part = request.getPart("file");
		String path = request.getServletContext().getRealPath("/upload/news");
		String fileName = path + File.separator + part.getSubmittedFileName();
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
		part.write(fileName);
		news.setImage(part.getSubmittedFileName());
		Integer count = newsService.update(news);
		String tip = count > 0 ? "修改成功" : "修改失败";
		request.setAttribute("tip", tip);
		request.getRequestDispatcher("news?method=listAll").forward(request, response);
	}

	private void publish(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String[] idStrings = request.getParameter("ids").split(",");
		Integer[] ids = new Integer[idStrings.length];
		for (int i = 0; i < idStrings.length; i++) {
			ids[i] = Integer.valueOf(idStrings[i]);
		}
		Integer count = newsService.publish(ids);
		String tip = count > 0 ? "发布成功" : "发布失败";
		request.setAttribute("tip", tip);
		request.getRequestDispatcher("news?method=listAll").forward(request, response);
	}
	private void allPublish(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Integer count=newsService.allPublish();
		String tip = count > 0 ? "发布成功" : "发布失败";
		request.setAttribute("tip", tip);
		request.getRequestDispatcher("news?method=listAll").forward(request, response);
	}
	private void listAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		pageModel.setPageIndex(1);
		pageModel.setPageSize(5);
		pageModel = newsService.listAll(pageModel);
		request.setAttribute("page", pageModel.getPageIndex());
		request.setAttribute("ps", pageModel.getTotalPage());
		request.setAttribute("news", pageModel.getList());
		request.getRequestDispatcher("news/list.jsp").forward(request, response);
	}

	private void listByName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		pageModel.setPageIndex((Integer) ConvertUtils.convert(request.getParameter("page"), Integer.class));
		pageModel.setPageSize((Integer) ConvertUtils.convert(request.getParameter("ps"), Integer.class));
		pageModel = newsService.listByName(title, pageModel);
		request.setAttribute("title", title);
		request.setAttribute("page", pageModel.getPageIndex());
		request.setAttribute("ps", pageModel.getTotalPage());
		request.setAttribute("news", pageModel.getList());
		request.getRequestDispatcher("news/list.jsp").forward(request, response);
	}

	private void toNewsCenter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<NewsType> newsTypes = newsService.listNewsType();
		Integer type=1;
		pageModel.setPageIndex(1);
		pageModel.setPageSize(6);
		pageModel = newsService.listByTypeAndStatus(type,1,pageModel);
		request.setAttribute("newsTypes", newsTypes);
		request.setAttribute("news", pageModel.getList());
		request.setAttribute("type", type);
		request.getRequestDispatcher("news.jsp").forward(request, response);
	}

	private void listByTypeAndStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer type = (Integer) ConvertUtils.convert(request.getParameter("type"), Integer.class);
		pageModel.setPageIndex((Integer) ConvertUtils.convert(request.getParameter("page"), Integer.class));
		pageModel.setPageSize(6);
		pageModel = newsService.listByTypeAndStatus(type, 1, pageModel);
		List<NewsType> newsTypes=newsService.listNewsType();
		request.setAttribute("page", pageModel.getPageIndex());
		request.setAttribute("ps", pageModel.getTotalPage());
		request.setAttribute("news", pageModel.getList());
		request.setAttribute("newsTypes", newsTypes);
		request.setAttribute("type", type);
		request.getRequestDispatcher("news.jsp").forward(request, response);
	}

	private void getDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = (Integer) ConvertUtils.convert(request.getParameter("id"), Integer.class);
		News news = newsService.get(id);
		List<NewsType> newsTypes=newsService.listNewsType();
		request.setAttribute("newsTypes", newsTypes);
		request.setAttribute("news", news);
		request.getRequestDispatcher("news_details.jsp").forward(request, response);
	}
}
