package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;

import java.util.List;
import java.util.Optional;

public interface AssigneeBeanI extends GenericBeanI<Assignee> {
    Assignee createAssignee(Assignee newAssignee);
    Optional<Assignee> getAssigneeByStaffId(String staffID);
}
