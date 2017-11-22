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
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

import com.web.bean.ProType;
import com.web.bean.Product;
import com.web.bean.User;
import com.web.service.ProductService;
import com.web.utils.PageModel;

@WebServlet("/product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10)
public class ProductController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3951965308372805578L;
	private ProductService productService = new ProductService();
	private PageModel<Product> pageModel = new PageModel<>();

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
			case "allPublish":
				allPublish(request, response);
				break;
			case "listAll":
				listAll(request, response);
				break;
			case "listByName":
				listByName(request, response);
				break;
			case "toProductCenter":
				toProductCenter(request, response);
				break;
			case "listByType":
				listByType(request, response);
				break;
			case "getDetail":
				getDetail(request, response);
				break;
			default:
				break;
			}
		}
	}

	private void toSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ProType> proTypes = productService.listProType();
		request.setAttribute("proTypes", proTypes);
		request.getRequestDispatcher("product/save.jsp").forward(request, response);
	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setContent(request.getParameter("content"));
		ProType proType = new ProType();
		proType.setId(Integer.valueOf(request.getParameter("type")));
		product.setProType(proType);
		User user = (User) request.getSession().getAttribute("user");
		product.setUser(user);
		Part part = request.getPart("file");
		String path = request.getServletContext().getRealPath("/upload/product") + File.separator
				+ part.getSubmittedFileName();
		File file = new File(path);
		if (file.exists()) {
			file.setReadable(true);
			file.setWritable(true);
			file.setExecutable(true);
			file.delete();
		}
		part.write(path);
		product.setImage(part.getSubmittedFileName());
		Integer count = productService.save(product);
		String tip = count > 0 ? "添加成功" : "添加失败";
		request.setAttribute("tip", tip);
		request.getRequestDispatcher("product?method=listAll").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		Integer count = productService.delete(id);
		String tip = count > 0 ? "删除成功" : "删除失败";
		request.setAttribute("tip", tip);
		request.getRequestDispatcher("product?method=listAll").forward(request, response);
	}

	private void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		Product product = productService.get(id);
		List<ProType> proTypes = productService.listProType();
		request.setAttribute("product", product);
		request.setAttribute("proTypes", proTypes);
		request.getRequestDispatcher("product/update.jsp").forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Product product = new Product();
		try {
			BeanUtils.populate(product, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		ProType proType = new ProType();
		proType.setId(Integer.valueOf(request.getParameter("type")));
		product.setProType(proType);
		Part part = request.getPart("file");
		String path = request.getServletContext().getRealPath(File.separator + "upload" + File.separator + "product")
				+ File.separator + part.getSubmittedFileName();
		File file = new File(path);
		if (file.exists()) {
			file.setReadable(true);
			file.setWritable(true);
			file.setExecutable(true);
			file.delete();
		}
		part.write(path);
		product.setImage(part.getSubmittedFileName());
		Integer count = productService.update(product);
		String tip = count > 0 ? "修改成功" : "修改失败";
		request.setAttribute("tip", tip);
		request.getRequestDispatcher("product?method=listAll").forward(request, response);
	}

	private void publish(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] strings = request.getParameter("ids").split(",");
		Integer[] ids = new Integer[strings.length];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = Integer.valueOf(strings[i]);
		}
		Integer count = productService.publish(ids);
		String tip = count > 0 ? "发布成功" : "发布失败";
		request.setAttribute("tip", tip);
		request.getRequestDispatcher("product?method=listAll").forward(request, response);
	}

	private void allPublish(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer count = productService.allPublish();
		String tip = count > 0 ? "发布成功" : "发布失败";
		request.setAttribute("tip", tip);
		request.getRequestDispatcher("product?method=listAll").forward(request, response);
	}

	private void listAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		pageModel.setPageIndex(1);
		pageModel.setPageSize(5);
		pageModel = productService.listAll(pageModel);
		request.setAttribute("page", pageModel.getPageIndex());
		request.setAttribute("ps", pageModel.getTotalPage());
		request.setAttribute("products", pageModel.getList());
		request.getRequestDispatcher("product/list.jsp").forward(request, response);
	}

	private void listByName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		pageModel.setPageIndex((Integer) ConvertUtils.convert(request.getParameter("page"), Integer.class));
		pageModel.setPageSize(5);
		pageModel = productService.listByName(name, pageModel);
		request.setAttribute("name", name);
		request.setAttribute("page", pageModel.getPageIndex());
		request.setAttribute("ps", pageModel.getTotalPage());
		request.setAttribute("products", pageModel.getList());
		request.getRequestDispatcher("product/list.jsp").forward(request, response);
	}

	private void toProductCenter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer type = 1;
		Integer status = 1;
		pageModel.setPageIndex(1);
		pageModel.setPageSize(16);
		pageModel = productService.listByTypeAndStatus(type, status, pageModel);
		List<ProType> proTypes = productService.listProType();
		request.setAttribute("type", type);
		request.setAttribute("products", pageModel.getList());
		request.setAttribute("proTypes", proTypes);
		request.getRequestDispatcher("product.jsp").forward(request, response);
	}

	private void listByType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer type = Integer.valueOf(request.getParameter("type"));
		Integer status = 1;
		pageModel.setPageIndex((Integer) ConvertUtils.convert(request.getParameter("page"), Integer.class));
		pageModel.setPageSize(16);
		pageModel = productService.listByTypeAndStatus(type, status, pageModel);
		List<ProType> proTypes = productService.listProType();
		request.setAttribute("type", type);
		request.setAttribute("products", pageModel.getList());
		request.setAttribute("proTypes", proTypes);
		request.getRequestDispatcher("product.jsp").forward(request, response);
	}

	private void getDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		Product product = productService.get(id);
		List<ProType> proTypes = productService.listProType();
		request.setAttribute("product", product);
		request.setAttribute("proTypes", proTypes);
		request.getRequestDispatcher("pro_details.jsp").forward(request, response);
	}
}
