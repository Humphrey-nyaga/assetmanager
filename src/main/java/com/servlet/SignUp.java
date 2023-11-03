package com.servlet;

import com.assetmanager.app.bean.UserBean;
import com.assetmanager.app.bean.UserBeanI;
import com.assetmanager.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignUp extends HttpServlet {


    UserBeanI userBeanI = new UserBean();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("./signup.html");
    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        if (password.equals(confirmPassword)) {
            User user = userBeanI.registerUser(username, password);
            System.out.println("*****************USER CREATED**********************");
            System.out.println("");
            System.out.println("User is: " + user.toString());
            System.out.println("");
            resp.sendRedirect("./login");
        }
    }


}
