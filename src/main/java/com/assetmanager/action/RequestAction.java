package com.assetmanager.action;

import com.assetmanager.app.bean.AssetRequestBean;
import com.assetmanager.app.bean.AssetRequestBeanI;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.exceptions.AssigneeDoesNotExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/request")

public class RequestAction extends BaseAction {
    AssetRequestBeanI assetRequestBean = new AssetRequestBean();
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        renderPage(servletRequest, servletResponse,"./request", AssetRequest.class,assetRequestBean.list(AssetRequest.class));

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
            assetRequestBean.create(assetRequest);
            servletResponse.sendRedirect("./");
        }catch(AssigneeDoesNotExistException e) {
            servletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "ERROR" +  e.getMessage());

        }

    }
}