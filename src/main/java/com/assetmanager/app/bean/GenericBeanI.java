package com.assetmanager.app.bean;

import java.io.Serializable;
import java.util.List;

public interface GenericBeanI<T> extends Serializable {

    List<T> list();

    T create(T entity);
    T update(T entity);

    void delete(T entity);

}