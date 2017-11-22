package com.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/image")
public class ImageController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7868601108379155430L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("show");
		if (StringUtils.isNotEmpty(method)) {
			switch (method) {
			case "news":
				showNewsImage(request, response);
				break;
			case "product":
				showProductImage(request, response);
			default:
				break;
			}
		}
	}

	private void showNewsImage(HttpServletRequest request, HttpServletResponse response)
			throws FileNotFoundException, IOException {
		String path = request.getParameter("path");
		if (StringUtils.isNotEmpty(path)) {
			path = request.getServletContext().getRealPath("/upload/news") + File.separator + path;
			File file = new File(path);
			InputStream inputStream = new FileInputStream(file);
			response.setContentType("image/*");
			OutputStream outputStream = response.getOutputStream();
			IOUtils.copy(inputStream, outputStream);
		}
	}

	private void showProductImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getParameter("path");
		if (StringUtils.isNotEmpty(path)) {
			path = request.getServletContext().getRealPath("/upload/product") + File.separator + path;
			File file = new File(path);
			InputStream inputStream = new FileInputStream(file);
			response.setContentType("image/*");
			OutputStream outputStream = response.getOutputStream();
			IOUtils.copy(inputStream, outputStream);
		}
	}

}
