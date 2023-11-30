package com.assetmanager.app.model.entity;

public enum MaintenancePeriod {
    WEEKLY("Weekly"),
    BI_WEEKLY("Bi-Weekly"),
    MONTHLY("Monthly"),
    QUARTERLY("Quarterly");

    private final String name;
     MaintenancePeriod(String name){
        this.name = name;
    }
    public String getName(){
         return  name;
    }

}
