package com.assetmanager.servlet;

import com.assetmanager.app.view.html.BasePage;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/assignee")
public class AssetAssignee extends HttpServlet {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        HttpSession httpSession = servletRequest.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {
            new BasePage().renderHtml(servletRequest, servletResponse,
                    "","./assignee");

        }else {
            servletResponse.sendRedirect("./");
        }
    }
}
