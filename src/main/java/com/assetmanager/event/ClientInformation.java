package com.assetmanager.event;

import com.assetmanager.util.logger.FileLogger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class ClientInformation implements ServletRequestListener {
    private static final Logger LOGGER = FileLogger.getLogger();

    public void requestInitialized(ServletRequestEvent sre)
    {
        ServletRequest request = sre.getServletRequest();
        LOGGER.info("***Decoding Incoming Servlet Request***");
        LOGGER.info("Client Locale : " + request.getLocale());
        LOGGER.info("Request Protocol : " + request.getProtocol());
        LOGGER.info("Client IP : " + request.getLocalAddr());
    }
    public void requestDestroyed(ServletRequestEvent sre){
        System.out.println("Servlet Request Destroyed");
    }
}
