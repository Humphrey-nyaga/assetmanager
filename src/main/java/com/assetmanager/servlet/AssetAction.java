package com.assetmanager.servlet;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssetBeanImpl;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.view.dropdowns.AssetCategoryDropdown;
import com.assetmanager.app.view.html.BasePage;
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

    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {


        new BasePage().renderHtml(servletRequest, servletResponse,

                "  <div class=\"row no-gutters\">\n" +
                        "    <div class=\"col-md-4 p-2 ml-2\">" +
                        "<div class=\"asset-container mx-auto\" style=\"\">\n" +
                        "    <form method=\"POST\" action=\"./asset\" class=\" border border-4\">\n" +
                        "        <h4 class=\"text-center mb-0 mt-0\">Create New Asset</h4>\n" +
                        "    <div class=\"mb-1 mt-0 p-2\">\n" +
                        "       <label for=\"assetId\" class=\"form-label\">Asset ID</label>\n" +
                        "       <input type=\"text\" class=\"form-control form-control-sm\" id=\"assetId\" name=\"assetId\">\n" +
                        "     </div>\n" +
                        "      <div class=\"mb-0 p-2\">\n" +
                        "        <label for=\"name\" class=\"form-label\">Name</label>\n" +
                        "        <input type=\"text\" class=\"form-control form-control-sm\" id=\"name\" name=\"name\">\n" +
                        "      </div>\n" +
                        "      <div class=\"mb-0 p-2\">\n" +
                        "        <label for=\"description\" class=\"form-label\">Description</label>\n" +
                        "        <textarea class=\"form-control  form-control-sm\" id=\"description\" name=\"description\"></textarea>\n" +
                        "      </div>\n" +
                        "      <div class=\"mb-0 p-2\">\n" +
                        "        <label for=\"dateAcquired\" class=\"form-label \">Date Acquired</label>\n" +
                        "        <input type=\"date\" class=\"form-control form-control-sm\" id=\"dateAcquired\" name=\"dateAcquired\">\n" +
                        "      </div>\n" +
                        "      <div class=\"mb-0 p-2\">\n" +

                        new AssetCategoryDropdown().menu()
                        +
                        "      </div>\n" +
                        "      <div class=\"mb-0 p-2\">\n" +
                        "        <label for=\"purchaseValue\" class=\"form-label\">Purchase Value</label>\n" +
                        "        <input type=\"number\" step=\"0.01\" class=\"form-control form-control-sm\" id=\"purchaseValue\" name=\"purchaseValue\">\n" +
                        "      </div>\n" +
                        "        <div class=\"d-grid gap-2 p-2\">\n" +
                        "            <button class=\"btn btn-primary\" type=\"submit\">Create Asset</button>\n" +
                        "        </div>\n" +
                        "    </form>\n" +
                        "    </div> </div> \n" +

                        "   <div class=\"col-md-8 mr-0\">" +
                        "<div class=\"table-responsive-sm\">\n" +
                        "        <div style=\"max-height: 60vh; overflow: auto;\">"
                        +
                        "    <table class=\"table table-bordered\">\n" +
                        "      <thead class=\"table-success\">\n" + //
                        "      <tr>\n" + //
                        "        <th scope=\"col\">ID</th>\n" +
                        "        <th scope=\"col\">Name</th>\n" +
                        "        <th scope=\"col\">Description</th>\n" +
                        "        <th scope=\"col\">Category</th>\n" + //
                        "        <th scope=\"col\">Date Added</th>\n" + //
                        "        <th scope=\"col\">Value</th>\n" + //
                        "      </tr>\n" + //
                        "      </thead>\n" + //
                        "      <tbody>\n </div> " +
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

