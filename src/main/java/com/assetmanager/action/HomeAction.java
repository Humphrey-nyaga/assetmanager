package com.assetmanager.action;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssetRequestBeanI;
import com.assetmanager.app.bean.AssigneeBeanI;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.AssetRequest;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.model.entity.UserRole;
import com.assetmanager.app.service.AssetsValuation;
import com.assetmanager.app.service.AssigneeService;
import com.assetmanager.app.service.RequestsService;
import com.assetmanager.app.view.html.OverviewRenderFormat;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/home")
public class HomeAction extends BaseAction {
//    @EJB
//    AssetsValuationI assetsValuation;

    @EJB
    AssetBeanI assetBean;
    @EJB
    AssetRequestBeanI assetRequestBean;

    @EJB
    AssigneeBeanI assigneeBean;

    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        HttpSession session = servletRequest.getSession();
        UserRole userRole = (UserRole) session.getAttribute("role");

        switch (userRole) {
            case ADMIN:
                String assetsSummary = OverviewRenderFormat.generateHtml(AssetsValuation.class, assetBean.list(new Asset()));
                String assetRequestsSummary = OverviewRenderFormat.generateHtml(RequestsService.class, assetRequestBean.list(new AssetRequest()));
                String assigneesSummary = OverviewRenderFormat.generateHtml(AssigneeService.class, assigneeBean.list(new Assignee()));

                StringBuilder stringBuilder = new StringBuilder()
                        .append(assetsSummary)
                        .append(assetRequestsSummary)
                        .append(assigneesSummary);
                String summary = stringBuilder.toString();

                servletRequest.setAttribute("content", summary);

//        renderPageWithoutTables(servletRequest, servletResponse, summary
//                , "./home");
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("./app/home.jsp");
                requestDispatcher.forward(servletRequest, servletResponse);
                break;
            case REGULAR:
                RequestDispatcher req = servletRequest.getRequestDispatcher("./asset");
                req.forward(servletRequest, servletResponse);

        }
    }


}
