package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;

import javax.ejb.Local;
import java.util.Optional;
@Local
public interface AssigneeBeanI extends GenericBeanI<Assignee> {
    Optional<Assignee> getAssigneeByStaffId(String staffID);
}
