package com.assetmanager.app.view.toolbars;

import java.io.Serializable;

public class Header implements Menu, Serializable {
    @Override
    public String menu() {
        return  "  <header class=\"p-3 mb-3 border-bottom\">\n" + //
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
                "\n";
    }

}
