package com.assetmanager.servlet;

import com.assetmanager.app.view.html.BasePage;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/request")

public class Requests extends HttpServlet {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        HttpSession httpSession = servletRequest.getSession();

            new BasePage().renderHtml(servletRequest, servletResponse,
                    """
                            <div class="row">
                                       <div class="col-sm-6">
                                           <div class="card h-100">
                                               <div class="card-header bg-secondary">
                                                   In Progress Asset Requests
                                               </div>
                                               <div class="card-body d-flex">
                                                   <table class="table">
                                                       <thead>
                                                           <tr>
                                                               <th>Asset Name</th>
                                                               <th>Requester</th>
                                                               <th>Status</th>
                                                           </tr>
                                                       </thead>
                                                       <tbody>
                                                           <tr>
                                                               <td>MacBook Pro (13-inch, M2, 2022)</td>
                                                               <td>Humphrey</td>
                                                               <td><button class="btn btn-warning">In Progress</button></td>
                                                           </tr>
                                                           <tr>
                                                               <td>ASUS PA329Q Monitor</td>
                                                               <td>John Doe</td>
                                                               <td><button class="btn btn-warning">In Progress</button></td>
                                                           </tr>
                                                           <tr>
                                                               <td>Cisco 4000S Router</td>
                                                               <td>Jane Doe</td>
                                                               <td><button class="btn btn-warning">In Progress</button></td>
                                                           </tr>
                                                       </tbody>
                                                   </table>
                                               </div>
                                           </div>
                                       </div>
                                       <div class="col-sm-6">
                                           <div class="card h-100">
                                               <div class="card-header bg-secondary">
                                                   Pending Asset Requests
                                               </div>
                                               <div class="card-body d-flex">
                                                   <table class="table">
                                                       <thead>
                                                           <tr>
                                                               <th>Asset Name</th>
                                                               <th>Requester</th>
                                                               <th>Status</th>
                                                           </tr>
                                                       </thead>
                                                       <tbody>
                                                           <tr>
                                                               <td>Tesla Model S</td>
                                                               <td>Humphrey</td>
                                                               <td><button class="btn btn-danger">Pending</button></td>
                                                           </tr>
                                                           <tr>
                                                               <td>JavaMaster 2000 Coffee Machine</td>
                                                               <td>Developer Whoami</td>
                                                               <td><button class="btn btn-danger">Pending</button></td>
                                                           </tr>
                                                           <tr>
                                                               <td>Ford F-150 Truck</td>
                                                               <td>Driver Joe</td>
                                                               <td><button class="btn btn-danger">Pending</button></td>
                                                           </tr>
                                                       </tbody>
                                                   </table>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                            ""","./request");

    }
}
