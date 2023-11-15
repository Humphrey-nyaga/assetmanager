package com.assetmanager.app.bean;

import com.assetmanager.app.model.entity.Assignee;

import java.util.List;

public interface AssigneeBeanI {
    Assignee createAssignee(Assignee newAssignee);
    Assignee updateAssignee(Assignee newAssignee);
    void deleteAssignee(Assignee newAssignee);
    List<Assignee> getAllAssignees();
    Assignee getAssigneeByNationalId();
    Assignee getAssigneeByEmail();
}
