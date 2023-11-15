package com.assetmanager.auth;

import com.assetmanager.app.bean.AuthBean;
import com.assetmanager.app.bean.AuthBeanI;
import com.assetmanager.app.model.entity.User;
import com.assetmanager.servlet.BaseAction;
import com.assetmanager.util.logger.FileLogger;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Logger;


@WebServlet(urlPatterns = "/login")
public class Login extends BaseAction {
    private static final Logger LOGGER = FileLogger.getLogger();
    AuthBeanI authBean = new AuthBean();

    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        HttpSession httpSession = servletRequest.getSession();
        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {
            servletResponse.sendRedirect("./home");
        }
        else {
            servletResponse.sendRedirect("./");
        }
    }

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        User user = new User();
        serializeForm(user, servletRequest.getParameterMap());

        if (authBean.authenticate(user)) {
            HttpSession httpSession = servletRequest.getSession(true);
            httpSession.setAttribute("loggedInId", new Date().getTime() + "");
            httpSession.setAttribute("username", user.getUsername());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String currentDate = LocalDate.now().format(formatter);
            httpSession.setAttribute("currentDate", currentDate);
            servletResponse.sendRedirect("./home");
            LOGGER.info("User Logged In Sucessfully");

        } else {
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

            servletResponse.sendRedirect("./index.jsp");
        }

    }
}