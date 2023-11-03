package com.assetmanager.event;

import com.assetmanager.app.bean.UserBean;
import com.assetmanager.app.model.User;
import com.assetmanager.database.Database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static com.assetmanager.database.Database.getDatabaseInstance;

@WebListener
public class AppInit implements ServletContextListener {
    UserBean userBean = new UserBean();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("*************** Asset Manager database Initialized *************");
        Database database = getDatabaseInstance();
        database.getUsersList().add(userBean.registerUser("admin.test", "admin"));
        database.getUsersList().add(userBean.registerUser("hum.admin", "root"));
        database.getUsersList().add(userBean.registerUser("john", "12345"));

        System.out.println("Context Initialized");
    }
}
