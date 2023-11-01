package com.assetmanager.app;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class Home extends HttpServlet {
    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("./home.html");
        requestDispatcher.forward(servletRequest, servletResponse);
    }
}
