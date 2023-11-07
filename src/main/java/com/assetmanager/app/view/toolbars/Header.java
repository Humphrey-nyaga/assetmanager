package com.assetmanager.app.view.toolbars;

import com.assetmanager.app.model.entity.MenuLink;
import com.assetmanager.app.model.entity.MenuLinkStatus;

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
        return """
                <header class="p-2 mb-3 border-bottom navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">
                            <img src="https://i.etsystatic.com/13930112/r/il/299b7a/4866387148/il_1140xN.4866387148_7oh3.jpg" width="35" height="35" class="d-inline-block align-top" alt="">
                            <b>Asset Manager</b>
                        </a>
                   <div class="container">
                       <div class="d-flex justify-content-between align-items-center">
                                           
                """ +

        new Header().insertUrls(activeUrl) +

                     """
                                  </div>
                                 </div>
                                          <div class="dropdown">
                                              <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                                                  <img src="https://icons.veryicon.com/png/o/miscellaneous/two-color-icon-library/user-286.png" alt="mdo" width="32" height="32" class="rounded-circle">
                                              </a>
                                              <ul class="dropdown-menu dropdown-menu-end profile-dropdown-menu text-small" aria-labelledby="dropdownUser1">
                                                  <li><a class="dropdown-item" href="#">Settings</a></li>
                                                  <li><a class="dropdown-item" href="#">Profile</a></li>
                                                  <li><hr class="dropdown-divider"></li>
                                                  <li><a class="dropdown-item" href="./logout">Sign out</a></li>
                                              </ul>
                                          </div>  
                             </header>

                             """;
    }


    public String insertUrls(String activeLinkLabel) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ul class=\"navbar-nav\">\n");

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
