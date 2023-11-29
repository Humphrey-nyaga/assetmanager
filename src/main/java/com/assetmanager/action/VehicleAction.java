package com.assetmanager.action;

import com.assetmanager.app.bean.VehicleBeanI;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.BaseEntity;
import com.assetmanager.app.model.entity.UserRole;
import com.assetmanager.app.model.entity.vehicle.Vehicle;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/vehicle/*")
public class VehicleAction extends BaseAction {
    @EJB
    VehicleBeanI vehicleBean;
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        renderPage(servletRequest,
                servletResponse,
                "./asset",
                Vehicle.class,
               vehicleBean.list(new Vehicle()));
    }
}
