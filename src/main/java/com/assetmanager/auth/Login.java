package com.assetmanager.auth;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/login", initParams = {
        @WebInitParam(name = "username", value = "admin"),
        @WebInitParam(name = "password", value = "admin")
})
public class Login extends HttpServlet {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        HttpSession httpSession = servletRequest.getSession();
        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))){
            servletResponse.sendRedirect("./home");
        }
        servletResponse.sendRedirect("./");
    }

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        HttpSession httpSession = servletRequest.getSession(true);
        httpSession.setAttribute("loggedInId", new Date().getTime()+"");

        PrintWriter printWriter = servletResponse.getWriter();

//        String username = servletRequest.getParameter("username");
//        String password = servletRequest.getParameter("password");
        ServletContext servletContext = getServletContext();
        String username = servletContext.getInitParameter("username");
        String password = servletContext.getInitParameter("password");
        servletContext.setAttribute("username", username);
        if (username.equals(getInitParameter("username")) && password.equals(getInitParameter("password"))) {
            // RequestDispatcher requestDispatcher =
            // servletRequest.getRequestDispatcher("/http-hello");
            // requestDispatcher.include(servletRequest, servletResponse);
            RequestDispatcher requestDispatcher =
                    servletRequest.getRequestDispatcher("/home");
            System.out.println("Request fetched");
            requestDispatcher.include(servletRequest, servletResponse);

            //servletResponse.sendRedirect("/home");
        }

    }
}
