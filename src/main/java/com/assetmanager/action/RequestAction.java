package com.assetmanager.action;

import com.assetmanager.app.bean.AssetRequestBeanI;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.exceptions.AssigneeDoesNotExistException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/request/*")

public class RequestAction extends BaseAction {
   @EJB
   AssetRequestBeanI assetRequestBean;
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        renderPage(servletRequest, servletResponse,"./request", AssetRequest.class,assetRequestBean.list(new AssetRequest()));

    }


//TODO - implement tables to remove + HtmlComponent.form(new AssetRequest()) +
    /*
     * Add asset requests
     * Link assignee to requests
     * Design page for normal user
     * Distinguish admin from user
     * */

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        try {
            AssetRequest assetRequest = new AssetRequest();
            serializeForm(assetRequest, servletRequest.getParameterMap());
            System.out.println("ASSET REQUEST >>>>>" + assetRequest);
            assetRequestBean.addOrUpdate(assetRequest);
            servletResponse.sendRedirect("./");
        }catch(AssigneeDoesNotExistException e) {
            servletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "ERROR" +  e.getMessage());
        }

    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && pathInfo.length() > 1) {
            String idString = pathInfo.substring(1);

            try {
                Long id = Long.parseLong(idString);
                assetRequestBean.deleteById(AssetRequest.class, id);

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