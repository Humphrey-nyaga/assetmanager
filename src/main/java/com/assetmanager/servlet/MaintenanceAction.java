package com.assetmanager.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/maintenance")
public class MaintenanceAction extends BaseAction {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        renderPageWithoutTables(servletRequest, servletResponse,
                    "<h2>Maintenance Schedule Page Coming Soon</b>" 
                            ,"./maintenance");

    }
}
