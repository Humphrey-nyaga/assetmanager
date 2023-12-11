package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Category;
import com.assetmanager.app.model.entity.Machinery.Machinery;
import com.assetmanager.util.SerialIDGenerator.SerialIDGenerator;
import com.assetmanager.util.idgenerator.GenericIDGenerator;

import javax.crypto.Mac;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

@Stateless
public class MachineryBean extends GenericBean<Machinery> implements MachineryBeanI {


    @Inject
    @Named("MachineryID")
    SerialIDGenerator serialIDGenerator;

    @Override
    public Machinery addOrUpdate(Machinery machinery) {
        System.out.println("MACHINERY>>>>>>>>" + machinery);
        machinery.setSerialNumber(serialIDGenerator.generate());
        machinery.setCategory(Category.MACHINERY);
        return getDao().addOrUpdate(machinery);
    }

}
