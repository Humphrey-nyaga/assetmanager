package com.assetmanager.action;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.dto.AssignAssetDTO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/assignAsset")
public class AssignAssetAction extends BaseAction {
    @EJB
    AssetBeanI assetBean;

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        AssignAssetDTO assignAssetDTO = new AssignAssetDTO();
        serializeForm(assignAssetDTO,servletRequest.getParameterMap());
        System.out.println(">>>>>>>>>>Asset TO ASSIGN " + assignAssetDTO);

        assetBean.assignAssetToAssignee(assignAssetDTO);
        servletResponse.sendRedirect("./asset");


    }
}
