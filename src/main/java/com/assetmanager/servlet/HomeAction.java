package com.assetmanager.servlet;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssetBeanImpl;
import com.assetmanager.app.view.html.BasePage;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class HomeAction extends HttpServlet {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        HttpSession httpSession = servletRequest.getSession();
        AssetBeanI assetBeanI = new AssetBeanImpl();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {

            new BasePage().renderHtml(servletRequest, servletResponse,
                    "  <div class=\"table-responsive-sm\">\n" +
                            "    <table class=\"table table-bordered table-sm table-responsive\">\n" +
                            "      <thead class=\"table-success\">\n" + //
                            "      <tr>\n" + //
                            "        <th scope=\"col\">ID</th>\n" +
                            "        <th scope=\"col\">Name</th>\n" +
                            "        <th scope=\"col\">Description</th>\n" +
                            "        <th scope=\"col\">Date Added</th>\n" + //
                            "        <th scope=\"col\">Category</th>\n" + //
                            "        <th scope=\"col\">Value</th>\n" + //
                            "      </tr>\n" + //
                            "      </thead>\n" + //
                            "      <tbody>\n" + //
                            "      \n" + assetBeanI.getAllAssets(),"./home"
            );
        } else
            servletResponse.sendRedirect("./");
    }


}
