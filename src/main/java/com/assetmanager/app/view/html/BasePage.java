package com.assetmanager.app.view.html;

import com.assetmanager.app.view.css.BaseCss;
import com.assetmanager.app.view.toolbars.Header;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class BasePage implements Serializable {
    public void renderHtml(HttpServletRequest request, HttpServletResponse response,
                           String content,String activeUrl) throws IOException {
        HttpSession httpSession = request.getSession();
        ServletContext ctx = request.getServletContext();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(
                    "<!doctype html>\n" +
                    "<html lang=\"en\">\n" +
                    "  <head>\n" + //
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + //
                    "    <meta name=\"description\" content=\"\">\n" + //
                    "\n" +
                    "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n"
                    + new BaseCss().getStyle() +
                    "  </head>\n" +
                    "  <body>\n"
                    + new Header().menu(activeUrl)
                    + "<h3>" + ctx.getInitParameter("AppName") + "</h3>"
                     +"<p> Welcome " + httpSession.getAttribute("username") + "</p>"
            );
            printWriter.write(content);
            printWriter.write(
                    "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>\n"+
                    "  </body>\n" +
                    "</html>\n");

        }
    }
}
