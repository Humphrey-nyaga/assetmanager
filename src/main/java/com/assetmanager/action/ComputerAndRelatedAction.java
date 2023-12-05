package com.assetmanager.action;

import com.assetmanager.app.bean.ComputerBeanI;
import com.assetmanager.app.bean.VehicleBeanI;
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
}