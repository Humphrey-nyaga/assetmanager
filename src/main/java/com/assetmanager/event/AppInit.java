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


    @Override
    public void contextInitialized(ServletContextEvent sce) {


        FileLogger.getLogger();
        LOGGER.info("*************** Asset Manager database Initialized *************");

        try {
            Reflections reflections = new Reflections("com.assetmanager.app.model.entity");
            List<Class<?>> entities = reflections.getTypesAnnotatedWith(DbTable.class)
                    .stream().toList();

            Connection conn = MysqlDatabase.getDatabaseInstance().getConnection();

            for (Class<?> clazz : entities) {
                if (!clazz.isAnnotationPresent(DbTable.class))
                    continue;
                DbTable dbTable = clazz.getAnnotation(DbTable.class);
                StringBuilder stringBuilder = new StringBuilder()
                        .append("CREATE TABLE IF NOT EXISTS ").append(dbTable.name()).append("(");

                String prefix = "";

                List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

                for (Field field : fields) {
                    field.setAccessible(true);

                    if (!field.isAnnotationPresent(DbColumn.class))
                        continue;

                    DbColumn dbColumn = field.getAnnotation(DbColumn.class);
                    stringBuilder.append(prefix)
                            .append(dbColumn.name())
                            .append(" ")
                            .append(dbColumn.definition())
                            .append(field.isAnnotationPresent(NotNull.class) ? " NOT NULL" : "")
                            .append(field.isAnnotationPresent(PrimaryKey.class) ? " AUTO_INCREMENT PRIMARY KEY" : "");
                    prefix = ", ";

                }

                stringBuilder.append(");");
                System.out.println("SQL CODE >> " + stringBuilder);

                PreparedStatement createTableStmt = conn.prepareStatement(stringBuilder.toString());
                createTableStmt.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        AssetBeanI assetBean = new AssetBeanImpl();


//////        LOGGER.info("*************** Creating Default Assets *************");
//        assetBean.insert(new Asset("001", "Laptop", "Dell Laptop", LocalDate.of(2022, 5, 10), Category.ELECTRONICS,
//                new BigDecimal("99999.99")));
//        assetBean.insert(new Asset("002", "Software License", "Microsoft Office", LocalDate.of(2021, 8, 15), Category.SOFTWARE,
//                new BigDecimal("14999.99")));
//        assetBean.insert(new Asset("003", "Server", "HP ProLiant Server", LocalDate.of(2021, 12, 5), Category.ELECTRONICS,
//                new BigDecimal("25000.00")));
//        assetBean.insert(new Asset("004", "Operating System", "Windows 10", LocalDate.of(2020, 3, 2), Category.SOFTWARE,
//                new BigDecimal("11000.50")));
//        assetBean.insert(new Asset("005", "Digital Artwork", "Abstract Painting", LocalDate.of(2023, 2, 18),
//                Category.DIGITAL, new BigDecimal("3999.99")));
//AssigneeBeanI assigneeBean = new AssigneeBean();
//
//        /*Create some default assignees*/
//        assigneeBean.insert(new Assignee("SN001", "Hans", "Schmidt", "hans@gmail.com",
//                LocalDate.of(1985, 5, 15), "DE123456789"));
//
//        assigneeBean.insert(new Assignee( "SN002", "Henry", "Müller", "henry@gmail.com",
//                LocalDate.of(1990, 8, 22), "DE987654321"));
//        assigneeBean.insert(new Assignee( "SN003", "Pablo", "Kevo", "kevo@gmail.com",
//                LocalDate.of(1982, 11, 7), "ES876543210"));
//
//AssetRequestBeanI assetRequestBean = new AssetRequestBean();
//
//        assetRequestBean.insert(new AssetRequest( "SN001", "MacBook M1", "M2 2022 Grey", LocalDate.now(), 2, RequestStatusEnum.PENDING));
//        assetRequestBean.insert(new AssetRequest( "SN002", "MacBook M2", "M2 2021 Silver", LocalDate.now(), 1, RequestStatusEnum.APPROVED));
//
//        assetRequestBean.insert(new AssetRequest(
//                "SN003",
//                "HP Laptop",
//                "EliteBook Intel Core i7.",
//                LocalDate.now(),
//                2,
//                RequestStatusEnum.PENDING
//        ));
//
//        assetRequestBean.insert(new AssetRequest(
//
//                "SN002",
//                "HP Laptop",
//                "HP Spectre x360, Intel Core i5",
//                LocalDate.now(),
//                1,
//                RequestStatusEnum.REJECTED
//        ));
//
//        assetRequestBean.insert(new AssetRequest(
//
//                "SN002",
//                "Pickup Truck",
//                "Ford F-150 Pickup.",
//                LocalDate.now(),
//                1,
//                RequestStatusEnum.APPROVED
//        ));

//        assetRequestBean.create(new AssetRequest(
//                "ASR006",
//                "SN001",
//                "Pickup Truck",
//                "Chevrolet Silve
//                rado 1500 Pickup.",
//                LocalDate.now(),
//                1,
//                RequestStatusEnum.PENDING
//        ));
//

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Context has been destroyed");
    }

}
