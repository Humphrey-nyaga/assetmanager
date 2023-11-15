package com.assetmanager.servlet;

import com.assetmanager.app.bean.MaintenanceBean;
import com.assetmanager.app.bean.MaintenanceBeanI;
import com.assetmanager.app.model.entity.Maintenance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/maintenance")
public class MaintenanceAction extends BaseAction {
    MaintenanceBeanI maintenanceBean = new MaintenanceBean();
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        renderPage(servletRequest, servletResponse
                            ,"./maintenance", Maintenance.class,maintenanceBean.list());

    }
}
