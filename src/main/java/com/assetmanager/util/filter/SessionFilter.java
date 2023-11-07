package com.assetmanager.util.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;


@WebFilter(urlPatterns = "/*")
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();
        String requestPath = httpServletRequest.getServletPath();

        if (session.isNew()) {
            session.invalidate();

            if (requestPath.equals("/login") || requestPath.equals("/index.html") || requestPath.equals("/signup.html") || requestPath.equals("/signup") || requestPath.equals("/")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
                servletResponse.getWriter().flush();
            }

        } else {
            if (StringUtils.isNotBlank((String) session.getAttribute("loggedInId"))) {
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
                servletResponse.getWriter().flush();

            }
        }
    }

}