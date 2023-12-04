package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.Optional;
@Remote
public interface AssigneeBeanI extends GenericBeanI<Assignee> {
    Optional<Assignee> getAssigneeByStaffId(String staffID);
}
