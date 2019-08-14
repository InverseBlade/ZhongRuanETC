package com.zhongruan.etc.filter;

import com.zhongruan.etc.bean.UserInfo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("user");

        if (userInfo == null && !request.getRequestURI().contains("/verify.do")
                && !request.getRequestURI().contains("/login.jsp")) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
