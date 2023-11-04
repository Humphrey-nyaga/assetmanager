package com.assetmanager.servlet;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Category;
import com.assetmanager.app.view.dropdowns.AssetCategoryDropdown;
import com.assetmanager.app.view.html.BasePage;
import com.assetmanager.database.Database;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.html.HTMLTableCaptionElement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.time.LocalDate;

@WebServlet("/asset")
public class AssetAction extends HttpServlet {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        HttpSession httpSession = servletRequest.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {

            new BasePage().renderHtml(servletRequest, servletResponse,
                    " <div class=\"asset-container mx-auto\" style=\"max-height: 80vh;\">\n" +
                            "    <form method=\"POST\" action=\"./asset\" class=\"col-6 border p-3 border-4\">\n" +
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
                            "    </div>\n");
        }
    }

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        Database database = Database.getDatabaseInstance();
        HttpSession httpSession = servletRequest.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {

            String assetId = servletRequest.getParameter("assetId");
            String name = servletRequest.getParameter("name");
            String description = servletRequest.getParameter("description");
            Category category = Category.valueOf(servletRequest.getParameter("category"));
            LocalDate dateAcquired = LocalDate.parse(servletRequest.getParameter("dateAcquired"));
            BigDecimal purchaseValue = new BigDecimal(servletRequest.getParameter("purchaseValue"));
            Asset asset = new Asset(assetId,name,description,dateAcquired,category,purchaseValue);
            database.getAssetList().add(asset);
        }
    }
}
