package com.assetmanager.action;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssetBeanImpl;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.database.Database;
import com.assetmanager.util.logger.FileLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


@WebServlet("/asset")
public class AssetAction extends BaseAction {
    private static final Logger LOGGER = FileLogger.getLogger();
    AssetBeanI assetBean = new AssetBeanImpl();
    Database database = Database.getDatabaseInstance();


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
        LOGGER.info(" Proceeding to serialize Asset");
        serializeForm(createAsset, servletRequest.getParameterMap());

        assetBean.create(createAsset);
        servletResponse.sendRedirect("./asset");


    }
}

