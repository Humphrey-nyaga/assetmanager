package com.assetmanager.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssetBeanImpl;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(urlPatterns = "/home")
public class DeprecatedHome extends HttpServlet {

    public void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws ServletException, IOException {
        HttpSession httpSession = servletRequest.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {
            AssetBeanI assetBeanI = new AssetBeanImpl();

            PrintWriter printWriter = servletResponse.getWriter();
            printWriter.print("<!doctype html>\n" + //
                    "<html lang=\"en\">\n" + //
                    "  <head>\n" + //
                    "    <meta charset=\"utf-8\">\n" + //
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + //
                    "    <meta name=\"description\" content=\"\">\n" + //
                    "<script src=\"https://code.jquery.com/jquery-3.7.1.min.js\" integrity=\"sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=\" crossorigin=\"anonymous\"></script>"
                    + //
                    "\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n"
                    + //
                    "    <style>\n" + //
                    "      .bd-placeholder-img {\n" + //
                    "        font-size: 1.125rem;\n" + //
                    "        text-anchor: middle;\n" + //
                    "        -webkit-user-select: none;\n" + //
                    "        -moz-user-select: none;\n" + //
                    "        user-select: none;\n" + //
                    "      }\n" + //
                    "\n" + //
                    "      @media (min-width: 768px) {\n" + //
                    "        .bd-placeholder-img-lg {\n" + //
                    "          font-size: 3.5rem;\n" + //
                    "        }\n" + //
                    "      }\n" + //
                    "    </style>\n" + //
                    "  </head>\n" + //
                    "  <body>\n" + //
                    "    \n" + //
                    "\n" + //
                    "<main>\n" + //
                    "\n" + //
                    "  <header class=\"p-3 mb-3 border-bottom\">\n" + //
                    "    <div class=\"container\">\n" + //
                    "      <div class=\"d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start\">\n"
                    + //
                    "        <a href=\"/\" class=\"d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none\">\n"
                    + //
                    "          <svg class=\"bi me-2\" width=\"40\" height=\"32\" role=\"img\" aria-label=\"Bootstrap\"><use xlink:href=\"#\"/></svg>\n"
                    + //
                    "        </a>\n" + //
                    "\n" + //
                    "        <ul class=\"nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0\">\n" + //
                    "            <li><a href=\"#\" class=\"nav-link px-2 link-secondary\">Overview</a></li>\n" + //
                    "            <li><a href=\"#\" class=\"nav-link px-2 link-dark\">Assets</a></li>\n" + //
                    "            <li><a href=\"#\" class=\"nav-link px-2 link-dark\">Customers</a></li>\n" + //
                    "            <li><a href=\"#\" class=\"nav-link px-2 link-dark\">Maintenance</a></li>\n" + //
                    "            <li><a href=\"#\" class=\"nav-link px-2 link-dark\">Reports</a></li>\n" + //
                    "            <li><a href=\"#\" class=\"nav-link px-2 link-dark\">Transactions</a></li>\n" + //
                    "          </ul>\n" + //
                    "          \n" + //
                    "\n" + //
                    "        <form class=\"col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3\">\n" + //
                    "          <input type=\"search\" class=\"form-control\" placeholder=\"Search...\" aria-label=\"Search\">\n"
                    + //
                    "        </form>\n" + //
                    "\n" + //
                    "        <div class=\"dropdown text-end\">\n" + //
                    "          <a href=\"#\" class=\"d-block link-dark text-decoration-none dropdown-toggle\" id=\"dropdownUser1\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">\n"
                    + //
                    "            <img src=\"https://icons.veryicon.com/png/o/miscellaneous/two-color-icon-library/user-286.png\" alt=\"mdo\" width=\"32\" height=\"32\" class=\"rounded-circle\">\n"
                    + //
                    "          </a>\n" + //
                    "          <ul class=\"dropdown-menu text-small\" aria-labelledby=\"dropdownUser1\">\n" + //
                    "            <li><a class=\"dropdown-item\" href=\"#\">Settings</a></li>\n" + //
                    "            <li><a class=\"dropdown-item\" href=\"#\">Profile</a></li>\n" + //
                    "            <li><hr class=\"dropdown-divider\"></li>\n" + //
                    "            <li><a class=\"dropdown-item\" href=\"./logout\">Sign out</a></li>\n" + //
                    "          </ul>\n" + //
                    "        </div>\n" + //log
                    "      </div>\n" + //
                    "    </div>\n" + //
                    "  </header>\n" + //
                    "\n" + //
                    "\n" + //
                    "  <div>\n" + //
                    "    <table class=\"table table-striped\">\n" + //
                    "      <thead>\n" + //
                    "      <tr>\n" + //
                    "        <th scope=\"col\">ID</th>\n" + //
                    "        <th scope=\"col\">Name</th>\n" + //
                    "        <th scope=\"col\">Description</th>\n" + //
                    "        <th scope=\"col\">Date Added</th>\n" + //
                    "        <th scope=\"col\">Category</th>\n" + //
                    "        <th scope=\"col\">Value</th>\n" + //
                    "      </tr>\n" + //
                    "      </thead>\n" + //
                    "      <tbody>\n" + //
                    "      \n" + //
                    "");
            printWriter.write(assetBeanI.getAllAssets());

            printWriter.write(
                    "      </tbody>\n" + //
                            "    </table>\n" + //
                            "  </div>\n" + //
                            "</main>\n" + //
                            "\n" + //
                            "\n" + //
                            "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>\n"
                            + //
                            "      \n" + //
                            "  </body>\n" + //
                            "</html>\n");
        } else
            servletResponse.sendRedirect("./");
    }

}
