package com.assetmanager.action;

import com.assetmanager.app.bean.VehicleBeanI;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.computer.Computer;
import com.assetmanager.app.model.entity.vehicle.Vehicle;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vehicle/*")
public class VehicleAction extends BaseAction {
    @EJB
    VehicleBeanI vehicleBean;
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        System.out.println(">>>>>>>>>>>vehicle " + vehicleBean.list(new Vehicle()));
        renderPage(servletRequest,
                servletResponse,
                "./vehicle",
                Vehicle.class,
               vehicleBean.list(new Vehicle()));


    }
    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        Vehicle vehicle = new Vehicle();
        serializeForm(vehicle, servletRequest.getParameterMap());
        vehicleBean.addOrUpdate(vehicle);
        servletResponse.sendRedirect("./asset");
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 1) {
            String idString = pathInfo.substring(1);

            try {
                Long id = Long.parseLong(idString);
                vehicleBean.deleteById(Vehicle.class, id);

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
