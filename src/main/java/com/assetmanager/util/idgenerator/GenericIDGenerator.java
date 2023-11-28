package com.assetmanager.util.idgenerator;

import com.assetmanager.database.helper.DbTable;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class GenericIDGenerator {
    @Inject
    MySqlMaxRowId mySqlMaxRowId;
    public String generateId(Object entity){
        Class<?> clazz = entity.getClass();
        if(!clazz.isAnnotationPresent(IdPrefix.class))
            throw new RuntimeException("Object Class Does Not Implement " + IdPrefix.class + " Id could not be generated");
        if(!clazz.isAnnotationPresent(DbTable.class))
            throw new RuntimeException("Object Class Does Not Implement " + DbTable.class + " Id could not be generated");

        IdPrefix idPrefix = clazz.getAnnotation(IdPrefix.class);
        String prefix = idPrefix.prefix();

        DbTable dbTable = clazz.getAnnotation(DbTable.class);
        String table =  dbTable.name();

        long id = mySqlMaxRowId.getLastGeneratedId(table) + 1; //ThreadLocalRandom.current().nextInt(1, 100);
        int currentYear = LocalDate.now().getYear() ;
        int dayOfYear = LocalDate.now().getDayOfYear();


        return  prefix + id +"-" +dayOfYear+currentYear;
    }

}
