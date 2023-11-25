package com.assetmanager.action;

import com.assetmanager.app.bean.MaintenanceBean;
import com.assetmanager.app.bean.MaintenanceBeanI;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Maintenance;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/maintenance/*")
public class MaintenanceAction extends BaseAction {
    @EJB
    MaintenanceBeanI maintenanceBean;
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        renderPage(servletRequest, servletResponse
                            ,"./maintenance", Maintenance.class,maintenanceBean.list(Maintenance.class));

    }
    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        Maintenance maintenance  = new Maintenance();
        serializeForm(maintenance, servletRequest.getParameterMap());

        maintenanceBean.create(maintenance);
        servletResponse.sendRedirect("./maintenance");

    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 1) {
            String idString = pathInfo.substring(1);

            try {
                Long id = Long.parseLong(idString);
                maintenanceBean.deleteById(Maintenance.class, id);

                response.setStatus(HttpServletResponse.SC_NO_CONTENT);

            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().println("Invalid ID Parsed");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
