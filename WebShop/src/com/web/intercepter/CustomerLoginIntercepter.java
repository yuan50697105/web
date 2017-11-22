package com.web.intercepter;

import com.web.bean.User;
import com.web.commons.WebCommons;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerLoginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(WebCommons.SESSION_USER);
        if (null == user) {
            if (!StringUtils.isEmpty(request.getParameter(WebCommons.GOODS_ARRAYS))) {
                session.setAttribute(WebCommons.GOODS_ARRAYS, request.getParameter(WebCommons.GOODS_ARRAYS));
            }
            session.setAttribute(WebCommons.CUSTOMER_ORDER_URL, request.getRequestURI());
            response.sendRedirect(request.getContextPath() + "/customer/toLogin");
            return false;
        } else {
            session.removeAttribute(WebCommons.REQUEST_ORDER_URL);
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(WebCommons.SESSION_USER);
        if (null != user) {
            request.getRequestDispatcher(request.getContextPath() + "/customer/order/toSubmit");
        }
    }

}
