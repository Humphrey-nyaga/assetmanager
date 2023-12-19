package com.assetmanager.action.regularuser;

import com.assetmanager.action.BaseAction;
import com.assetmanager.app.bean.AssigneeBeanI;
import com.assetmanager.app.model.entity.Assignee;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myHome")
public class myHomeAction extends BaseAction {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("./app/regularuser/myhome.jsp");
            dispatcher.forward(request, response);
        }
    }