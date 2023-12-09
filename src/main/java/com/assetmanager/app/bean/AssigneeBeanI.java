package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;

public interface AssigneeBeanI extends GenericBeanI<Assignee>{
    Assignee getAssigneeByStaffId(String staffToSearchID);
}
