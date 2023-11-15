package com.assetmanager.util.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

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
        HttpSession httpSession = httpServletRequest.getSession();
        String servletPath = httpServletRequest.getServletPath();

        if (httpSession.isNew() || StringUtils.isBlank((String) httpSession.getAttribute("loggedInId"))) {
            System.out.println("1.New Session");
            httpSession.invalidate();

            if (servletPath.equals("/login") || servletPath.equals("/signup") || servletPath.equals("/index.jsp") || servletPath.equals("/signup.jsp")) {
                filterChain.doFilter(servletRequest, servletResponse);

            } else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
                servletResponse.getWriter().flush();

            }

        } else {
            if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {
                httpServletResponse.addHeader("AuthTime", DateFormat.getDateTimeInstance().format(new Date()));
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
                servletResponse.getWriter().flush();

            }
        }

    }

}