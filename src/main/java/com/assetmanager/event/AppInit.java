package com.assetmanager.event;

import com.assetmanager.app.bean.*;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.util.logging.Logger;


@WebListener
public class AppInit implements ServletContextListener {
    @EJB
    AssetBeanI assetBean;
    @EJB
    AssetRequestBeanI assetRequestBean;
    @EJB
    AssigneeBeanI assigneeBean;



    @Override
    public void contextInitialized(ServletContextEvent sce) {

////        /*Create some default assignees*/
//        assigneeBean.create(new Assignee( "Hans", "Schmidt", "hans@example0.com",
//                LocalDate.of(1985, 5, 15), "DE123456789"));
//
//        assigneeBean.create(new Assignee( "Henry", "Müller", "henry@example0.com",
//                LocalDate.of(1990, 8, 22), "DE987654321"));
//        assigneeBean.create(new Assignee( "Pablo", "Kevo", "kevo@example.com",
//                LocalDate.of(1982, 11, 7), "ES876543210"));
//
////
//        assetRequestBean.create(new AssetRequest( "SN001", "MacBook M1", "M2 2022 Grey", LocalDate.now(), 2, RequestStatus.PENDING));
//        assetRequestBean.create(new AssetRequest( "SN002", "MacBook M2", "M2 2021 Silver", LocalDate.now(), 1, RequestStatus.APPROVED));
//
//        assetRequestBean.create(new AssetRequest(
//                "SN003",
//                "HP Laptop",
//                "EliteBook Intel Core i7.",
//                LocalDate.now(),
//                2,
//                RequestStatus.PENDING
//        ));
//
//        assetRequestBean.create(new AssetRequest(
//
//                "SN002",
//                "HP Laptop",
//                "HP Spectre x360, Intel Core i5",
//                LocalDate.now(),
//                1,
//                RequestStatus.REJECTED
//        ));
//
//        assetRequestBean.create(new AssetRequest(
//
//                "SN002",
//                "Pickup Truck",
//                "Ford F-150 Pickup.",
//                LocalDate.now(),
//                1,
//                RequestStatus.APPROVED
//        ));


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context has been destroyed");
    }

}
