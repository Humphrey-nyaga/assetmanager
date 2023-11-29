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
import javax.inject.Inject;
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
    @Inject
    OverviewRenderFormat summaryRender;

    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        HttpSession session = servletRequest.getSession();
        UserRole userRole = (UserRole) session.getAttribute("role");


        switch (userRole) {
            case ADMIN:
                String summary = getSummary();

                servletRequest.setAttribute("content", summary);

                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("./app/home.jsp");
                requestDispatcher.forward(servletRequest, servletResponse);
                break;
            case REGULAR:
                RequestDispatcher req = servletRequest.getRequestDispatcher("./asset");
                req.forward(servletRequest, servletResponse);

        }
    }

    private String getSummary() {
        String assetsSummary = summaryRender.generateHtml(AssetsValuation.class, assetBean.list(new Asset()));
        String assetRequestsSummary = summaryRender.generateHtml(RequestsService.class, assetRequestBean.list(new AssetRequest()));
        String assigneesSummary = summaryRender.generateHtml(AssigneeService.class, assigneeBean.list(new Assignee()));

        String summary = assetsSummary +
                assetRequestsSummary +
                assigneesSummary;
        return summary;
    }


}
