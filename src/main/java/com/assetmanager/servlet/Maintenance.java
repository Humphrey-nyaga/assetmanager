package com.assetmanager.servlet;

import com.assetmanager.app.view.html.BasePage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/maintenance")
public class Maintenance extends HttpServlet {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

            new BasePage().renderHtml(servletRequest, servletResponse,
                    "<h2>Maintenance Schedule Page Coming Soon</b>","./maintenance");

    }
}
