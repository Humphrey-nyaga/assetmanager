package com.assetmanager.action;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssetBeanImpl;
import com.assetmanager.app.model.entity.Asset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteAsset")
public class AssetDelete extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AssetBeanI assetBean = new AssetBeanImpl();
        Asset asset = new Asset();
        asset.setSerialNumber(req.getParameter("id"));
        assetBean.delete(asset);
        resp.sendRedirect("./asset");
    }
}
