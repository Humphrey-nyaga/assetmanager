package com.assetmanager.action.regularuser;

import com.assetmanager.action.BaseAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myRequests/*")
public class myRequestsAction extends BaseAction{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("./app/regularuser/myrequests.jsp");
        dispatcher.forward(request, response);
    }
}