package com.assetmanager.action;

import com.assetmanager.app.bean.AssetBeanI;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.database.Database;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/asset")
public class AssetAction extends BaseAction {
    @EJB
    AssetBeanI assetBean;

    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        renderPage(servletRequest,
                servletResponse,
                "./asset",
                Asset.class,
                assetBean.list(Asset.class));
    }

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        Asset createAsset = new Asset();
        serializeForm(createAsset, servletRequest.getParameterMap());

        assetBean.create(createAsset);
        servletResponse.sendRedirect("./asset");


    }
}

