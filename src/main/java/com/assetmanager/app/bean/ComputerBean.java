package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.computer.Computer;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class ComputerBean extends GenericBean<Computer> implements ComputerBeanI{
}
