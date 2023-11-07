package com.assetmanager.event;

import com.assetmanager.app.bean.AssetBeanI;
import com.assetmanager.app.bean.AssetBeanImpl;
import com.assetmanager.app.bean.UserBean;
import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Category;
import com.assetmanager.app.model.entity.User;
import com.assetmanager.database.Database;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.assetmanager.database.Database.getDatabaseInstance;

@WebListener
public class AppInit implements ServletContextListener {

    UserBean userBean = new UserBean();
    AssetBeanI assetBean = new AssetBeanImpl();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("*************** Asset Manager database Initialized *************");
        Database database = getDatabaseInstance();
        System.out.println("*************** Creating Default Users *************");

          User registerUser = new User();
          registerUser.setPassword("root");
          registerUser.setConfirmPassword("root");
          registerUser.setUsername("root");
          userBean.registerUser(registerUser);


        System.out.println("*************** Creating Default Assets *************");
        database.getAssetList().add(assetBean.createAsset(new Asset("001", "Laptop", "Dell Laptop", LocalDate.of(2022, 5, 10), Category.HARDWARE,
                new BigDecimal("99999.99"))));
        database.getAssetList().add(assetBean.createAsset(new Asset("002", "Software License", "Microsoft Office", LocalDate.of(2021, 8, 15), Category.SOFTWARE,
                new BigDecimal("14999.99"))));
        database.getAssetList().add(assetBean.createAsset(new Asset("003", "Server", "HP ProLiant Server", LocalDate.of(2021, 12, 5), Category.HARDWARE,
                new BigDecimal("25000.00"))));
        database.getAssetList().add(assetBean.createAsset(new Asset("004", "Operating System", "Windows 10", LocalDate.of(2020, 3, 2), Category.SOFTWARE,
                new BigDecimal("11000.50"))));
        database.getAssetList().add(assetBean.createAsset(new Asset("005", "Digital Artwork", "Abstract Painting", LocalDate.of(2023, 2, 18),
                Category.DIGITAL, new BigDecimal("3999.99"))));

        System.out.println("Context Initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
        System.out.println("Context has been destroyed");
    }

}
