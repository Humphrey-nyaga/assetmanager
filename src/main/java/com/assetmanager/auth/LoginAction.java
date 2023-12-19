package com.assetmanager.auth;

import com.assetmanager.app.bean.AuthBeanI;
import com.assetmanager.app.model.entity.User;
import com.assetmanager.action.BaseAction;
import com.assetmanager.app.model.entity.UserRole;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;


@WebServlet(urlPatterns = "/login")
public class LoginAction extends BaseAction {
    @EJB
    AuthBeanI authBean;

    @Inject
    JWTUtil jwtUtil;

    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        HttpSession httpSession = servletRequest.getSession();
        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {
            servletResponse.sendRedirect("./home");
        } else {
            servletResponse.sendRedirect("./");
        }
    }

    public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

        User user = new User();
        serializeForm(user, servletRequest.getParameterMap());
        User authenticatedUser = authBean.authenticate(user);

        String generatedToken = jwtUtil.generateToken(authenticatedUser);
        System.out.println("Token for: " + authenticatedUser.getUsername() + "Id: " + "---->>> " + generatedToken);

        System.out.println("generatedToken is valid= " + jwtUtil.isTokenValid(generatedToken,authenticatedUser));



        if (authenticatedUser != null && StringUtils.isNotBlank(authenticatedUser.getUsername())) {
            HttpSession httpSession = servletRequest.getSession(true);
            httpSession.setAttribute("loggedInId", new Date().getTime() + "");
            httpSession.setAttribute("username", authenticatedUser.getUsername());
            httpSession.setAttribute("role", authenticatedUser.getUserRole());

            String username = servletRequest.getParameter("username");
            String password = servletRequest.getParameter("password");

            String authToken = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
            servletResponse.setHeader("Authorization", "Bearer " + authToken);


            if (authenticatedUser.getUserRole() == UserRole.ADMIN)
                servletResponse.getWriter().write("{\"authToken\": \"" + authToken + "\"}");
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            if (authenticatedUser.getUserRole() == UserRole.REGULAR)
                servletResponse.sendRedirect("./asset");
        } else {
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

            servletResponse.sendRedirect("./index.jsp");
        }

    }
}