package com.assetmanager.util.SerialIDGenerator;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
@Named("AssigneeID")
public class AssigneeSerialIdGenerator implements SerialIDGenerator{
    @Override
    public String generate() {
        long id = ThreadLocalRandom.current().nextInt(1, 100);
        int currentYear = LocalDate.now().getYear() ;
        int dayOfYear = LocalDate.now().getDayOfYear();
        return  "ASN" + id +"-" +dayOfYear+currentYear;
    }
}
