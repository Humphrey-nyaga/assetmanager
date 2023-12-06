package com.assetmanager.action;

import com.assetmanager.app.bean.AssetRequestBeanI;
import com.assetmanager.app.bean.AssigneeBeanI;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Assignee;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateAssignee")
public class updateAssigneeAction extends BaseAction{
    @EJB
    AssigneeBeanI assigneeBean;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String assigneeId = request.getParameter("id");
        Assignee  assignee = assigneeBean.findById(Assignee.class,Long.valueOf(assigneeId));
        request.setAttribute("assignee", assignee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./app/updateAssignee.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestId = request.getParameter("id");

        Assignee assignee = new Assignee();
        serializeForm(assignee, request.getParameterMap());
        assignee.setId(Long.valueOf(request.getParameter("assigneeId")));
        System.out.println("Assignee UPDATED>>>>" + assignee);
        assigneeBean.addOrUpdate(assignee);
        response.sendRedirect("./assignee");
    }
}
