package com.assetmanager.servlet;

import com.assetmanager.app.bean.AssigneeBean;
import com.assetmanager.app.bean.AssigneeBeanI;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.view.html.HtmlComponent;

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

        HtmlComponent<Assignee> assigneeHtmlComponent = new HtmlComponent<>();
        renderPage(servletRequest,servletResponse,assigneeHtmlComponent.form(new Assignee())
                        + assigneeBean.getAllAssignees(),
                "./assignee");

    }
    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        Assignee assignee = new Assignee();
        serializeForm(assignee, servletRequest.getParameterMap());
        assigneeBean.createAssignee(assignee);
    }
}