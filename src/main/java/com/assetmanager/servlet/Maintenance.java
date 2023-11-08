package com.assetmanager.servlet;

import com.assetmanager.app.bean.UserBean;
import com.assetmanager.app.bean.UserBeanI;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.html.BasePage;
import com.assetmanager.app.view.html.HtmlComponent;
import com.assetmanager.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/maintenance")
public class Maintenance extends HttpServlet {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        Database database = Database.getDatabaseInstance();

            new BasePage().renderHtml(servletRequest, servletResponse,
                    "<h2>Maintenance Schedule Page Coming Soon</b>" 
                            ,"./maintenance");

    }
}
