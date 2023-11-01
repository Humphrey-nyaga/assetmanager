package com.assetmanager.auth;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login", initParams = {
        @WebInitParam(name = "username", value = "admin"),
        @WebInitParam(name = "password", value = "admin")
})
public class Login extends HttpServlet {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        servletResponse.sendRedirect("./");
    }

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        PrintWriter printWriter = servletResponse.getWriter();

        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");
        System.out.println(username);
        System.out.println(password);
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
