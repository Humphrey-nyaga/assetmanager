package com.assetmanager.action;

import com.assetmanager.app.bean.UserBeanI;
import com.assetmanager.app.model.entity.User;
import com.assetmanager.app.model.entity.UserRole;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/signup")
public class SignUpAction extends BaseAction {
    @EJB
    UserBeanI userBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("./signup.jsp");
        requestDispatcher.include(req, resp);
    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User registerUser = new User();
        registerUser.setUserRole(UserRole.ADMIN);
        serializeForm(registerUser, req.getParameterMap());

        if (userBean.registerUser(registerUser))
            resp.sendRedirect("./");
    }

}
