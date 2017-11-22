package com.web.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.web.bean.User;
import com.web.service.UserService;

@WebServlet("/user")
public class UserController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7626886200794963581L;
	private UserService userService = new UserService();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		switch (method) {
		case "login":
			login(request, response);
			break;
		case "loginOut":
			loginOut(request, response);
			break;
		default:
			break;
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		user = userService.get(user);
		if (user == null) {
			response.sendRedirect("info/login.jsp");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("admin/manager.jsp");
		}
	}

	private void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("info/login.jsp");
	}
}
