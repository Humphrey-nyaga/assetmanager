package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Machinery.Machinery;

import javax.ejb.Local;
import javax.ejb.Remote;

@Remote
public interface MachineryBeanI extends GenericBeanI<Machinery> {

}
