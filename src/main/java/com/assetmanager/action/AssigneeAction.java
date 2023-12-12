package com.assetmanager.action;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssigneeBeanI;
import com.assetmanager.app.model.entity.Assignee;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/assignee/*")
public class AssigneeAction extends BaseAction {
    @EJB
    AssigneeBeanI assigneeBean;

    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        renderPage(servletRequest, servletResponse,
                "./assignee", Assignee.class, assigneeBean.list(new Assignee()));

    }

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        Assignee assignee = new Assignee();
        serializeForm(assignee, servletRequest.getParameterMap());
        assigneeBean.addOrUpdate(assignee);
        servletResponse.sendRedirect("./assignee");
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String idString = pathInfo.substring(1);

            try {
                Long id = Long.parseLong(idString);
                Assignee assignee = assigneeBean.findById(Assignee.class, id);
                assigneeBean.deleteById(Assignee.class,id);

                response.setStatus(HttpServletResponse.SC_NO_CONTENT);

            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}


