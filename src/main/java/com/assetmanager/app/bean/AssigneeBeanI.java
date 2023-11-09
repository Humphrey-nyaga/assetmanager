package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;

public interface AssigneeBeanI {
    Assignee createAssignee(Assignee newAssignee);
    Assignee updateAssignee(Assignee newAssignee);
    void deleteAssignee(Assignee newAssignee);
    String getAllAssignees();
    Assignee getAssigneeByNationalId();
    Assignee getAssigneeByEmail();
}
