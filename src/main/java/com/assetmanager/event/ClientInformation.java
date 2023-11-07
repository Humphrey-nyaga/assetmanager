package com.assetmanager.event;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ClientInformation implements ServletRequestListener {

    public void requestInitialized(ServletRequestEvent sre)
    {
        ServletRequest request = sre.getServletRequest();
        System.out.println("***Decoding Incoming Servlet Request***");
        System.out.println("Client Locale : " + request.getLocale());
        System.out.println("Request Protocol : " + request.getProtocol());
        System.out.println("Client IP : " + request.getLocalAddr());
    }
    public void requestDestroyed(ServletRequestEvent sre){
        System.out.println("Servlet Request Destroyed");
    }
}
