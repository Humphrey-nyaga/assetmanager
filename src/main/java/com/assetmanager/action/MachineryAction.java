package com.assetmanager.action;


import com.assetmanager.app.bean.MachineryBeanI;
import com.assetmanager.app.bean.VehicleBeanI;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.BaseEntity;
import com.assetmanager.app.model.entity.Machinery.Machinery;
import com.assetmanager.app.model.entity.UserRole;
import com.assetmanager.app.model.entity.vehicle.Vehicle;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        machineryBean.create(machinery);
        servletResponse.sendRedirect("./asset");
    }

}
