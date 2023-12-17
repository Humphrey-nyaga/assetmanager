package com.assetmanager.action;

import com.assetmanager.app.bean.AssetRequestBeanI;
import com.assetmanager.app.model.entity.AssetRequest;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateRequest/*")
public class UpdateRequest extends BaseAction {
    @EJB
    AssetRequestBeanI assetRequestBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestId = request.getParameter("id");

        AssetRequest assetRequest = new AssetRequest();
        serializeForm(assetRequest, request.getParameterMap());
        assetRequest.setId(Long.valueOf(request.getParameter("assetRequestId")));
        System.out.println("ASSET UPDATED>>>>" + assetRequest);
        assetRequestBean.addOrUpdate(assetRequest);
        response.sendRedirect("./request");
    }
}
