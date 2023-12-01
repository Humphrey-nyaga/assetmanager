package com.assetmanager.util.SerialIDGenerator;

import javax.enterprise.inject.Specializes;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
@Specializes
public class GAEnterpriseAssigneeID extends AssigneeSerialIdGenerator {
    @Override
    public String generate() {
        long id = ThreadLocalRandom.current().nextInt(1, 100);
        String date = LocalDate.now().toString().replace("-", "");
        return  "EMP" + id +"-" +date;
    }

}
