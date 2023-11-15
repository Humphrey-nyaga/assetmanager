package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.*;
import com.assetmanager.database.Database;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class GenericBean<T> implements GenericBeanI<T> {
    Database database = Database.getDatabaseInstance();

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> list() {
        Class clazz = ((Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);


        if (clazz.equals(Asset.class))
            return (List<T>) database.getAssetList();

        if (clazz.equals(AssetRequest.class))
            return (List<T>) database.getAssetRequestsList();

        if (clazz.equals(Assignee.class))
            return (List<T>) database.getAssigneeList();

        if (clazz.equals(User.class))
            return (List<T>) database.getUsersList();

        return new ArrayList<>();
    }

    @Override
    public T create(T entity) {
        Database database = Database.getDatabaseInstance();

        Class clazz = entity.getClass();
        System.out.println(clazz.getName());

        if (entity instanceof Asset)
            database.getAssetList().add((Asset) entity);

        else if (entity instanceof AssetRequest)
            database.getAssetRequestsList().add((AssetRequest) entity);


        else if (entity instanceof User)
            database.getUsersList().add((User) entity);

        else if (entity instanceof Maintenance)
            database.getMaintenanceList().add((Maintenance) entity);

        else if (entity instanceof Assignee)
            ((Assignee) entity).setId(100L);
        if (entity instanceof Assignee) {
            database.getAssigneeList().add((Assignee) entity);
        }
        return entity;

    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }
}
