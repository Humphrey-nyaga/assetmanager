package com.assetmanager.action;

import com.assetmanager.app.bean.AssigneeBean;
import com.assetmanager.app.bean.AssigneeBeanI;
import com.assetmanager.app.model.entity.Assignee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/assignee")
public class AssigneeAction extends BaseAction {
    AssigneeBeanI assigneeBean = new AssigneeBean();
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        renderPage(servletRequest,servletResponse,
                "./assignee", Assignee.class,assigneeBean.list(Assignee.class));

    }
    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        Assignee assignee = new Assignee();
        serializeForm(assignee, servletRequest.getParameterMap());
        assigneeBean.create(assignee);
        servletResponse.sendRedirect("./assignee");
    }
}
