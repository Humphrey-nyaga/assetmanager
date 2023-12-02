package com.assetmanager.app.scheduler.maintenance;

import com.assetmanager.app.model.entity.MaintenanceFrequency;

import java.time.LocalDate;

public class MaintenanceScheduler {

    public LocalDate scheduleNextMaintenance(MaintenanceFrequency maintenanceFrequency, LocalDate dateAdded) {
        LocalDate nextMaintenance = null;
        switch (maintenanceFrequency) {
            case WEEKLY:
                nextMaintenance = dateAdded.plusWeeks(1);
                break;
            case BI_WEEKLY:
                nextMaintenance = dateAdded.plusWeeks(2);
            case MONTHLY:
                nextMaintenance = dateAdded.plusMonths(1);
                break;
            case QUARTERLY:
                nextMaintenance = dateAdded.plusMonths(3);
                break;
        }
        return nextMaintenance;
    }
}

