package com.assetmanager.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/report")

public class Reports extends BaseAction {
    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {

            renderPage(servletRequest, servletResponse,
                    """
                            <div class="container">
                                        <div class="row">
                                           <div class="col-md-4">
                                               <div class="card text-center">
                                                   <div class="card-body">
                                                       <h5 class="card-title">Asset Summary Report</h5>
                                                       <p class="card-text">View a summary of all your assets.</p>
                                                       <a href="#" class="btn btn-primary">View Report</a>
                                                   </div>
                                               </div>
                                           </div>
                                   
                                           <!-- Maintenance Report -->
                                           <div class="col-md-4">
                                               <div class="card text-center">
                                                   <div class="card-body">
                                                       <h5 class="card-title">Maintenance Report</h5>
                                                       <p class="card-text">Track maintenance schedules and history.</p>
                                                       <a href="#" class="btn btn-primary">View Report</a>
                                                   </div>
                                               </div>
                                           </div>
                                   
                                           <div class="col-md-4">
                                               <div class="card text-center">
                                                   <div class="card-body">
                                                       <h5 class="card-title">Valuation Report</h5>
                                                       <p class="card-text">Get asset valuation and depreciation details.</p>
                                                       <a href="#" class="btn btn-primary">View Report</a>
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                                                      
                                   </div>
                                   ""","./report");

    }
}
