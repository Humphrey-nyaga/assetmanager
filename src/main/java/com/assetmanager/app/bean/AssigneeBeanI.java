package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;

import java.util.Optional;

public interface AssigneeBeanI extends GenericBeanI<Assignee> {
    Assignee getAssigneeByStaffId(String staffID);
}
