package com.assetmanager.util.SerialIDGenerator;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Named("VehicleID")
public class VehicleSerialIDGenerator implements SerialIDGenerator{
    @Override
    public String generate() {
        long id = ThreadLocalRandom.current().nextInt(1, 100);
        int currentYear = LocalDate.now().getYear();
        int dayOfYear = LocalDate.now().getDayOfYear();
        return "ASN-VHC" + id + "-" + dayOfYear + currentYear;
    }
}
