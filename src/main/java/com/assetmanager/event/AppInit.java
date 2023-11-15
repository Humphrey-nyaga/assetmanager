package com.assetmanager.event;

import com.assetmanager.app.bean.*;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Assignee;
import com.assetmanager.app.model.entity.Category;
import com.assetmanager.app.model.entity.User;
import com.assetmanager.database.Database;
import com.assetmanager.util.logger.FileLogger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.logging.Logger;

import static com.assetmanager.database.Database.getDatabaseInstance;

@WebListener
public class AppInit implements ServletContextListener {
    private static final Logger LOGGER = FileLogger.getLogger();

    UserBean userBean = new UserBean();
    AssetBeanI assetBean = new AssetBeanImpl();
    AssigneeBeanI assigneeBean = new AssigneeBean();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        FileLogger.getLogger();
        LOGGER.info("*************** Asset Manager database Initialized *************");
        Database database = getDatabaseInstance();
        LOGGER.info("*************** Creating Default Users *************");

        User registerUser = new User();
        registerUser.setPassword("root");
        registerUser.setConfirmPassword("root");
        registerUser.setUsername("root");
        userBean.registerUser(registerUser);
        userBean.registerUser(new User("hum.admin", "root", "root"));
        userBean.registerUser(new User("test", "root", "root"));


        LOGGER.info("*************** Creating Default Assets *************");
        assetBean.createAsset(new Asset("001", "Laptop", "Dell Laptop", LocalDate.of(2022, 5, 10), Category.HARDWARE,
                new BigDecimal("99999.99")));
        assetBean.createAsset(new Asset("002", "Software License", "Microsoft Office", LocalDate.of(2021, 8, 15), Category.SOFTWARE,
                new BigDecimal("14999.99")));
        assetBean.createAsset(new Asset("003", "Server", "HP ProLiant Server", LocalDate.of(2021, 12, 5), Category.HARDWARE,
                new BigDecimal("25000.00")));
        assetBean.createAsset(new Asset("004", "Operating System", "Windows 10", LocalDate.of(2020, 3, 2), Category.SOFTWARE,
                new BigDecimal("11000.50")));
        assetBean.createAsset(new Asset("005", "Digital Artwork", "Abstract Painting", LocalDate.of(2023, 2, 18),
                Category.DIGITAL, new BigDecimal("3999.99")));


        /*Create some default assignees*/
        assigneeBean.createAssignee(new Assignee(1L, "SN001", "Hans", "Schmidt", "hans@gmail.com",
                LocalDate.of(1985, 5, 15), "DE123456789"));

        assigneeBean.createAssignee(new Assignee(2L, "SN002", "Henry", "Müller", "henry@gmail.com",
                LocalDate.of(1990, 8, 22), "DE987654321"));
        assigneeBean.createAssignee(new Assignee(3L, "SN003", "Pablo", "Kevo", "kevo@gmail.com",
                LocalDate.of(1982, 11, 7), "ES876543210"));


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Context has been destroyed");
    }

}
