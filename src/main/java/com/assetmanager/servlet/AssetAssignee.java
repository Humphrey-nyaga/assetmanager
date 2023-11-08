package com.assetmanager.servlet;

import com.assetmanager.app.view.html.BasePage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/assignee")
public class AssetAssignee extends HttpServlet {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        HttpSession httpSession = servletRequest.getSession();

            new BasePage().renderHtml(servletRequest, servletResponse,

                    """
                            <div class=col-md-12 justify-content-center>
                            <div class="d-flex justify-content-center">
                            <table class="table table-striped table-bordered ">  
                            <thead  class="thead-dark">  <tr>  
                                <th scope="col">Asset ID</th>
                                <th scope="col">Asset Name</th>
                                <th scope="col">Assignee ID</th>
                                <th scope="col">First Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">Date Assigned</th>
                                <th scope="col">Date Returned</th>
                              </tr>
                              </thead>
                              <tbody>
                              <tr>
                                <th scope="row">1</th>
                                <td>Laptop</td>
                                <td>101</td>
                                <td>John</td>
                                <td>Doe</td>
                                <td>2023-10-01</td>
                                <td>2023-10-15</td>
                              </tr>
                              <tr>
                                <th scope="row">2</th>
                                <td>Monitor</td>
                                <td>102</td>
                                <td>Jane</td>
                                <td>Smith</td>
                                <td>2023-10-05</td>
                                <td>2023-10-18</td>
                              </tr>
                              <tr>
                                <th scope="row">3</th>
                                <td>Keyboard</td>
                                <td>103</td>
                                <td>Mike</td>
                                <td>Johnson</td>
                                <td>2023-10-10</td>
                                <td>2023-10-20</td>
                              </tr>
                              </tbody>
                            </table>
                            </div></div>
                            ""","./assignee");

    }
}
