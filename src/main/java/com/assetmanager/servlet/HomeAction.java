package com.assetmanager.servlet;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssetBeanImpl;
import com.assetmanager.app.view.html.BasePage;
import com.assetmanager.app.view.html.OverviewHtml;
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
        OverviewHtml overviewHtml = new OverviewHtml();


            new BasePage().renderHtml(servletRequest, servletResponse, overviewHtml.getPageHtml()
                    ,"./home"
            );
    }

}
