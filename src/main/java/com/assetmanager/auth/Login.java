package com.assetmanager.auth;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(value = "/login",initParams = {
        @WebInitParam(name="username",value = "admin"),
        @WebInitParam(name = "password", value = "admin")
})
public class Login extends HttpServlet {
    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter printWriter = servletResponse.getWriter();

        String username = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");
        if(username.equals(getInitParameter(username)) && password.equals(getInitParameter(password))){
            printWriter.print("Welcome to Asset Manager");
        }

    }
}
