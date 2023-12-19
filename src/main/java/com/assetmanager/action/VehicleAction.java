package com.assetmanager.action;

import com.assetmanager.app.bean.VehicleBeanI;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Machinery.Machinery;
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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if(action!=null && action.equals("update")){
            String vehicleId = request.getParameter("id");
            Vehicle vehicle = vehicleBean.findById(Vehicle.class,Long.valueOf(vehicleId));
            request.setAttribute("vehicle", vehicle);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./app/updateVehicle.jsp");
            dispatcher.forward(request, response);
        }
        
        renderPage(request,
                response,
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
