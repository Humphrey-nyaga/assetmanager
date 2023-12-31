package com.assetmanager.action;


import com.assetmanager.app.bean.MachineryBeanI;
import com.assetmanager.app.model.entity.Machinery.Machinery;
import com.assetmanager.app.model.entity.vehicle.Vehicle;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/machinery/*")
public class MachineryAction extends BaseAction {
    @EJB
    MachineryBeanI machineryBean;
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        renderPage(servletRequest,
                servletResponse,
                "./asset",
                Machinery.class,
                machineryBean.list(new Machinery()));
    }


    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        Machinery machinery = new Machinery();
        serializeForm(machinery, servletRequest.getParameterMap());
        machineryBean.addOrUpdate(machinery);
        servletResponse.sendRedirect("./asset");
    }


    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 1) {
            String idString = pathInfo.substring(1);

            try {
                Long id = Long.parseLong(idString);
                machineryBean.deleteById(Machinery.class, id);

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
