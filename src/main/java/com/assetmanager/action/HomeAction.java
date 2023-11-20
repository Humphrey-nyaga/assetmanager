package com.assetmanager.action;

import com.assetmanager.app.view.html.OverviewHtml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeAction extends BaseAction {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        OverviewHtml overviewHtml = new OverviewHtml();

        renderPageWithoutTables(servletRequest, servletResponse, overviewHtml.getPageHtml()
                , "./home");

    }


}
