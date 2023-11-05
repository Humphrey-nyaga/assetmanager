package com.assetmanager.app.view.toolbars;

import com.assetmanager.app.model.view.MenuLink;
import com.assetmanager.app.model.view.MenuLinkStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Header implements Menu, Serializable {
    private List<MenuLink> links = new ArrayList<>();

    {
        links.add(new MenuLink("./home", "Overview", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./asset", "Assets", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./assignee", "Assignees", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./maintenance", "Maintenance", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./report", "Reports", MenuLinkStatus.NOT_ACTIVE));
        links.add(new MenuLink("./request", "Requests", MenuLinkStatus.NOT_ACTIVE));
      //  links.add(new MenuLink("./valuation", "Valuation", MenuLinkStatus.NOT_ACTIVE));

    }

    @Override
    public String menu(String activeUrl) {
        return "  <header class=\"p-3 mb-3 border-bottom navbar navbar-expand-lg navbar-light bg-light\n\">\n" + //
                "    <div class=\"container\">\n" + //
                "      <div class=\"d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start\">\n"
                + //
                "        <a href=\"/\" class=\"d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none\">\n"
                + //
                "          <svg class=\"bi me-2\" width=\"40\" height=\"32\" role=\"img\" aria-label=\"Bootstrap\"><use xlink:href=\"#\"/></svg>\n"
                +
                "        </a>\n" +
                "\n"
                +
                        new Header().insertUrls(activeUrl)
                +
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
    public String insertUrls(String activeLinkLabel) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ul class=\"nav navbar-nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0\">\n");

        for (MenuLink link : links) {
            String activeClass = link.getUrl().equals(activeLinkLabel) ? " active" : "";
            String url = "<li class=\"nav-item\">\n" +
                    "   <a class=\"nav-link px-2 link-dark" + activeClass + "\" href=" + link.getUrl() + ">" + link.getLabel() + "</a>\n" +
                    "</li>\n";
            stringBuilder.append(url);
        }

        stringBuilder.append("</ul>");
        return stringBuilder.toString();
    }



}
