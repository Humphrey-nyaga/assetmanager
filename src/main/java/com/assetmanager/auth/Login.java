package com.assetmanager.auth;

import com.assetmanager.app.bean.UserBean;
import com.assetmanager.app.bean.UserBeanI;
import com.assetmanager.app.model.User;
import com.assetmanager.database.Database;
import com.assetmanager.util.security.PasswordEncoderI;
import com.assetmanager.util.security.PasswordEncoderImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/login", initParams = {
        @WebInitParam(name = "", value = ""),
        @WebInitParam(name = "", value = "")
})
public class Login extends HttpServlet {
    Database database = Database.getDatabaseInstance();
    PasswordEncoderI passwordEncoder = new PasswordEncoderImpl();

    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        HttpSession httpSession = servletRequest.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {
            servletResponse.sendRedirect("./home");
        }
        servletResponse.sendRedirect("./");
    }

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        String password = servletRequest.getParameter("password");
        String username = servletRequest.getParameter("username");

        boolean userFound = false;

        for (User user : database.getUsersList()) {
            if (username.equals(user.getUsername()) && passwordEncoder.verifyPassword(password, user.getPassword())) {
                HttpSession httpSession = servletRequest.getSession(true);
                httpSession.setAttribute("loggedInId", new Date().getTime() + "");
                httpSession.setAttribute("username", username);
                userFound = true;
                break;
            }
        }

        if (userFound) {
            servletResponse.sendRedirect("./home");
        } else {
            servletResponse.sendRedirect("./");
        }

    }
}