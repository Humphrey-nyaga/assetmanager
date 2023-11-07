package com.assetmanager.servlet;

import com.assetmanager.app.bean.UserBean;
import com.assetmanager.app.bean.UserBeanI;
import com.assetmanager.app.model.entity.User;
import com.assetmanager.database.Database;
import com.assetmanager.util.logger.FileLogger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/signup")
public class SignUp extends BaseAction {
    private static final Logger LOGGER = FileLogger.getLogger();

    Database database = Database.getDatabaseInstance();
    UserBeanI userBean = new UserBean();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("./signup.html");
    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User registerUser = new User();
        serializeForm(registerUser,req.getParameterMap());
        userBean.registerUser(registerUser);

        resp.sendRedirect("./");
    }


}
