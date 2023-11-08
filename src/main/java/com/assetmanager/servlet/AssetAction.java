package com.assetmanager.servlet;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssetBeanImpl;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.dropdowns.AssetCategoryDropdown;
import com.assetmanager.app.view.html.BasePage;
import com.assetmanager.app.view.html.HtmlComponent;
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


        HtmlComponent<Asset> assetHtmlComponent = new HtmlComponent<>();
        new BasePage().renderHtml(servletRequest, servletResponse,

                assetHtmlComponent.form(new Asset()) +
                        assetBean.getAllAssets()
                        + "</tbody><table></div> " +
                        "</div> ", "./asset");

    }

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

//        String assetId = servletRequest.getParameter("assetId");
//        String name = servletRequest.getParameter("name");
//        String description = servletRequest.getParameter("description");
//        Category category = Category.valueOf(servletRequest.getParameter("category"));
//        LocalDate dateAcquired = LocalDate.parse(servletRequest.getParameter("dateAcquired"));
//        BigDecimal purchaseValue = new BigDecimal(servletRequest.getParameter("purchaseValue"));

        //database.getAssetList().add(new Asset(assetId, name, description, dateAcquired, category, purchaseValue));

        Asset createAsset = new Asset();
        LOGGER.info(" Proceeding to serialize Asset");
        serializeForm(createAsset, servletRequest.getParameterMap());
        assetBean.createAsset(createAsset);
        servletResponse.sendRedirect("./asset");


    }
}

