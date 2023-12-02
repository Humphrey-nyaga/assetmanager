package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Asset;
import com.assetmanager.app.model.entity.Machinery.Machinery;
import com.assetmanager.database.MysqlDatabase;
import com.assetmanager.util.idgenerator.GenericIDGenerator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MachineryBean extends GenericBean<Machinery> implements MachineryBeanI {


    @Inject
    GenericIDGenerator generate;

    @Override
    public void create(Machinery machinery) {
        machinery.setSerialNumber(generate.generateId(machinery));
        getDao().create(machinery);
    }

}
