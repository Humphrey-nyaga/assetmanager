package com.assetmanager.event;

import com.assetmanager.app.bean.*;
import com.assetmanager.app.model.entity.*;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.NotNull;
import com.assetmanager.database.helper.PrimaryKey;
import com.assetmanager.util.logger.FileLogger;
import org.reflections.Reflections;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


@WebListener
public class AppInit implements ServletContextListener {
    private static final Logger LOGGER = FileLogger.getLogger();
    @EJB
    AssetBeanI assetBean;
    @EJB
    AssetRequestBeanI assetRequestBean;
    @EJB
    AssigneeBeanI assigneeBean;



    @Override
    public void contextInitialized(ServletContextEvent sce) {


////////        LOGGER.info("*************** Creating Default Assets *************");
//        assetBean.create(new Asset("001", "Laptop", "Dell Laptop", LocalDate.of(2022, 5, 10), Category.ELECTRONICS,
//                new BigDecimal("99999.99")));
//        assetBean.create(new Asset("002", "Software License", "Microsoft Office", LocalDate.of(2021, 8, 15), Category.SOFTWARE,
//                new BigDecimal("14999.99")));
//        assetBean.create(new Asset("003", "Server", "HP ProLiant Server", LocalDate.of(2021, 12, 5), Category.ELECTRONICS,
//                new BigDecimal("25000.00")));
//        assetBean.create(new Asset("004", "Operating System", "Windows 10", LocalDate.of(2020, 3, 2), Category.SOFTWARE,
//                new BigDecimal("11000.50")));
//        assetBean.create(new Asset("005", "Digital Artwork", "Abstract Painting", LocalDate.of(2023, 2, 18),
//                Category.DIGITAL, new BigDecimal("3999.99")));
////
////        /*Create some default assignees*/
//        assigneeBean.create(new Assignee("SN001", "Hans", "Schmidt", "hans@gmail.com",
//                LocalDate.of(1985, 5, 15), "DE123456789"));
//
//        assigneeBean.create(new Assignee( "SN002", "Henry", "Müller", "henry@gmail.com",
//                LocalDate.of(1990, 8, 22), "DE987654321"));
//        assigneeBean.create(new Assignee( "SN003", "Pablo", "Kevo", "kevo@gmail.com",
//                LocalDate.of(1982, 11, 7), "ES876543210"));
//
////
//        assetRequestBean.create(new AssetRequest( "SN001", "MacBook M1", "M2 2022 Grey", LocalDate.now(), 2, RequestStatusEnum.PENDING));
//        assetRequestBean.create(new AssetRequest( "SN002", "MacBook M2", "M2 2021 Silver", LocalDate.now(), 1, RequestStatusEnum.APPROVED));
//
//        assetRequestBean.create(new AssetRequest(
//                "SN003",
//                "HP Laptop",
//                "EliteBook Intel Core i7.",
//                LocalDate.now(),
//                2,
//                RequestStatusEnum.PENDING
//        ));
//
//        assetRequestBean.create(new AssetRequest(
//
//                "SN002",
//                "HP Laptop",
//                "HP Spectre x360, Intel Core i5",
//                LocalDate.now(),
//                1,
//                RequestStatusEnum.REJECTED
//        ));
//
//        assetRequestBean.create(new AssetRequest(
//
//                "SN002",
//                "Pickup Truck",
//                "Ford F-150 Pickup.",
//                LocalDate.now(),
//                1,
//                RequestStatusEnum.APPROVED
//        ));
//

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Context has been destroyed");
    }

}
