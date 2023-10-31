package com.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
/*Create a servlet by implementing the Servlet interface*/
@WebServlet("/hello")

public class Hello implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("The hello servlet has been created!!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter printWriter = res.getWriter();
        printWriter.print("<b>Hello World</b>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Shutting down hello class!!");
    }
}
