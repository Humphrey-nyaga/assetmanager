package com.assetmanager.action;

import com.assetmanager.app.bean.ComputerBeanI;
import com.assetmanager.app.bean.VehicleBeanI;
import com.assetmanager.app.model.entity.Machinery.Machinery;
import com.assetmanager.app.model.entity.computer.Computer;
import com.assetmanager.app.model.entity.vehicle.Vehicle;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/computer/*")
public class ComputerAndRelatedAction extends BaseAction {
    @EJB
    ComputerBeanI computerBean;
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        renderPage(servletRequest,
                servletResponse,
                "./asset",
                Computer.class,
                computerBean.list(new Computer()));
    }
    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        Computer computer = new Computer();
        serializeForm(computer, servletRequest.getParameterMap());
        computerBean.addOrUpdate(computer);
        servletResponse.sendRedirect("./asset");
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 1) {
            String idString = pathInfo.substring(1);

            try {
                Long id = Long.parseLong(idString);
                computerBean.deleteById(Computer.class, id);

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