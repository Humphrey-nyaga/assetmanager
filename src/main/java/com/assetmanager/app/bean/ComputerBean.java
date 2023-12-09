package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.computer.Computer;
import com.assetmanager.util.SerialIDGenerator.SerialIDGenerator;
import com.assetmanager.util.idgenerator.GenericIDGenerator;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

@Stateless
@Remote
public class ComputerBean extends GenericBean<Computer> implements ComputerBeanI{
    @Inject
    @Named("ComputerID")
    SerialIDGenerator serialIDGenerator;
    @Override
    public Computer addOrUpdate(Computer computer) {
        computer.setSerialNumber(serialIDGenerator.generate());
        return super.addOrUpdate(computer);
    }
}
